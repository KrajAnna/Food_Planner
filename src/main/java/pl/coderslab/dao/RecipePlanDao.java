package pl.coderslab.dao;

import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipePlanDao {
    private static final String ADD_RECIPE_TO_PLAN_QUERY =
            "Insert Into recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) values (?, ?, ?, ?, ?);";

    public void create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECIPE_TO_PLAN_QUERY)) {
            preparedStatement.setInt(1, recipePlan.getRecipeId());
            preparedStatement.setString(2, recipePlan.getMealType());
            preparedStatement.setInt(3, recipePlan.getDisplayOrder());
            preparedStatement.setInt(4, recipePlan.getDayNameId());
            preparedStatement.setInt(5, recipePlan.getPlanId());
            int result = preparedStatement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
