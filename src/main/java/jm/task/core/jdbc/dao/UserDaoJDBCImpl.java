package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.sql.Delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private static final String createTable = "CREATE TABLE IF NOT EXISTS users" +
            "(id BIGSERIAL PRIMARY KEY, " +
            "name TEXT, " +
            "lastName TEXT," +
            "age INTEGER)";


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(createTable)) {
            (statement).execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = (PreparedStatement) Util.getConnectionDataBase().prepareStatement("DROP TABLE IF EXISTS users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnectionDataBase().prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnectionDataBase().prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
       List<User> users = new ArrayList<>();

        System.out.println("-------------//////////---------");
       try ( Connection connectionDataBase = Util.getConnectionDataBase()) {
           ResultSet resultSet = connectionDataBase.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

        public void cleanUsersTable() {
        try (PreparedStatement statement = Util.getConnectionDataBase().prepareStatement("TRUNCATE TABLE users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}