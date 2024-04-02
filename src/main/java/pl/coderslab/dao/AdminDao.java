package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;

public class AdminDao {
    private static final String FIND_ADMIN_BY_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?,  last_name=?,  email=?,  password=?, superadmin=?,enable=? WHERE id=?";
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String FIND_ADMIN_QUERY = "SELECT * FROM admins WHERE email=?";

    public Admin create(Admin admin) {
        String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, admin.isSuperadmin());
            statement.setBoolean(6, admin.isEnable());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nie udało sie stworzyc administratora");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new SQLException("Nie udalo sie stworzyc administratora, brak id");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Admin findAdminByEmail(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getBoolean("superadmin"));
                admin.setEnable(resultSet.getBoolean("enable"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    public Admin checkAuthorization(String email, String password) {
        Admin admin = findAdminByEmail(email);
        String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
            return admin;
        } else return null;

    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()); // Hashujemy nowe hasło
                statement.setString(4, hashedPassword);
            }
            statement.setBoolean(5, admin.isSuperadmin());
            statement.setBoolean(6, admin.isEnable());
            statement.setInt(7, admin.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nie udało sie dokonąc Update dla admina");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin findAdminById(int adminId) {
        Admin admin = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getBoolean("superadmin"));
                admin.setEnable(resultSet.getBoolean("enable"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

}
