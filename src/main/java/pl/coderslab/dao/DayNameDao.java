package pl.coderslab.dao;


import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAY_NAMES_QUERY = "select * from day_name ORDER BY display_order ASC;";

    public List<DayName> findAll() {
        List<DayName> dayNames = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DAY_NAMES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int displayOrder = resultSet.getInt("display_order");
                dayNames.add(new DayName(id, name, displayOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNames;
    }
}
