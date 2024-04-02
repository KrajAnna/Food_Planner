package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.lang.reflect.Array;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeDao {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String READ_RECIPE_QUERY = "select * from recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "select * from recipe;";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ?, ingredients = ?, description = ?," +
            "created = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created," +
            "updated, preparation_time, preparation, admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String FIND_ALL_RECIPES_USER_QUERY = "select * from recipe where admin_id =?;";


    public Recipe getRecipe(int id) {
        if (id < 1) {
            return null;
        }
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_RECIPE_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return recipeFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Wszystkie pola w tabeli recipe muszą być uzupełnione - inaczej nic nie zwróci
     *
     * @return
     */
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recipes.add(recipeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    public int findAllUserRecipes(int admin_id) {
        int i = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_RECIPES_USER_QUERY)) {
            preparedStatement.setInt(1, admin_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i += 1;
            }
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            setStatement(recipe, preparedStatement);
            int querySuccess = preparedStatement.executeUpdate();

            if (querySuccess != 1) {
                throw new RuntimeException("Execute update returned " + querySuccess);
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.first()) {
                recipe.setId(generatedKeys.getInt(1));
                return recipe;
            } else {
                throw new RuntimeException("Generated key was not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ID recipe nie może być powiązany z recipe_plan - jeśli jest to nie zostanie usunięty
     *
     * @param id
     */
    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            setStatement(recipe, preparedStatement);
            preparedStatement.setInt(9, recipe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStatement(Recipe recipe, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, recipe.getName());
        preparedStatement.setString(2, recipe.getIngredients().toString().replaceAll("[\\[\\]]", ""));
        preparedStatement.setString(3, recipe.getDescription());
        preparedStatement.setString(4, recipe.getCreated().format(formatter));
        preparedStatement.setString(5, recipe.getUpdated().format(formatter));
        preparedStatement.setInt(6, recipe.getPreparationTime());
        preparedStatement.setString(7, recipe.getPreparation());
        preparedStatement.setInt(8, recipe.getAdminId());
    }

    private Recipe recipeFromResultSet(ResultSet resultSet) throws SQLException {
        int recipeId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String[] ingredientsArray = resultSet.getString("ingredients").split(", ");
        List<String> ingredients = Arrays.asList(ingredientsArray);
        String description = resultSet.getString("description");
        LocalDateTime created = LocalDateTime.parse(resultSet.getString("created"), formatter);
        LocalDateTime updated = LocalDateTime.parse(resultSet.getString("updated"), formatter);
        int prepTime = resultSet.getInt("preparation_time");
        String preparation = resultSet.getString("preparation");
        int adminId = resultSet.getInt("admin_id");
        return new Recipe(recipeId, name, ingredients, description,
                created, updated, prepTime, preparation, adminId);
    }
}
