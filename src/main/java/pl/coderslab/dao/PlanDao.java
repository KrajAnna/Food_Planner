package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.model.LastPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDao {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name, description, created, admin_id) VALUES (?,?,?,?);";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE plan SET name =?, description =? WHERE id=?;"; //zakladam, ze nie bedzie mozliwa edycja daty utworzenia planu
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PlANS_USER_QUERY = "select * from plan where admin_id =?;";
    private static final String FIND_LAST_PlANS_USER_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description " +
            "FROM `recipe_plan` " +
            "JOIN day_name on day_name.id=day_name_id " +
            "JOIN recipe on recipe.id=recipe_id WHERE " +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) " +
            "ORDER by day_name.display_order, recipe_plan.display_order;";


    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(3, plan.getCreated().format(formatter));
            insertStm.setString(2, plan.getDescription());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public int findAllPlansRecipes(int admin_id) {
        int i = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PlANS_USER_QUERY)) {
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

    public Map<String, List<LastPlan>> findLastPlan(int admin_id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LAST_PlANS_USER_QUERY)) {
            preparedStatement.setInt(1, admin_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LastPlan> lastPlanRecipes = new ArrayList<>();
            while (resultSet.next()) {
               System.out.println(resultSet.getString("day_name"));
                System.out.println(resultSet.getString("recipe_name"));
                System.out.println(resultSet.getString("meal_name"));
                System.out.println();
                LastPlan plan = new LastPlan();
                plan.setDayName(resultSet.getString("day_name"));
                plan.setRecipeName(resultSet.getString("recipe_name"));
                plan.setRecipeDescription(resultSet.getString("recipe_description"));
                plan.setMealName(resultSet.getString("meal_name"));
                lastPlanRecipes.add(plan);
                System.out.println(plan);
            }
            Map<String, List<LastPlan>> groupedByDay = new HashMap<>();

            for (LastPlan plan : lastPlanRecipes) {
                String dayName = plan.getDayName();
                System.out.println(dayName);
                List<LastPlan> dayRecipes = groupedByDay.get(dayName);
                if (dayRecipes == null) {
                    dayRecipes = new ArrayList<>();
                    groupedByDay.put(dayName, dayRecipes);
                }
                dayRecipes.add(plan);
                System.out.println(groupedByDay);
         }

            return groupedByDay;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(3, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {

            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setDescription(resultSet.getString("description"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }






}
//    private int id;
//    private String name;
//    private String description;
//    private LocalDateTime created;
//    private int adminId;
