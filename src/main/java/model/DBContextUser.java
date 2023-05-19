package model;

import model.Enums.Role;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContextUser {
    protected static final Logger LOGGER = Logger.getLogger(DBContextUser.class.getName());

    public DBContextUser() {}

    public String createInsertQuery(User user) {
        String ID = "'" + user.getID() + "'";
        String name = "'" + user.getName() + "'";
        String username = "'" + user.getUsername() + "'";
        String password = "'" + user.getPassword() + "'";
        String role = "'" + user.getRole().toString() + "'";

        String query = "INSERT INTO user VALUES( " +  ID + ", " + name + ", " + username + ", " + password + ", " + role + ")";

        return query;
    }

    public String createDeleteQueryByID(int id) {
        String query = "DELETE FROM user WHERE id = " + id;

        return query;
    }

    public String createDeleteQueryByUsername(String username) {
        String query = "DELETE FROM user WHERE username = '" + username + "'";

        return query;
    }

    public String createFindQueryByID(int id) {
        String query = "SELECT id, name, username, password, role FROM user WHERE id = " + id;

        return query;
    }

    public String createFindQueryByUsername(String username) {
        return "SELECT id, name, username, password, role FROM user WHERE username = '" + username + "'";
    }

    public String createFindQueryByUsernameAndPassword(String username, String password) {
        return "SELECT id, name, username, password, role FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
    }

    public String createFindQueryByRole(String role) {
        return "SELECT id, name, username, password, role FROM user WHERE role = '" + role + "'";
    }

    public String createFindAllQuery() {
        return "SELECT id, name, username, password, role FROM user";
    }

    public String createUpdateQuery(int id, String name, String username, String password, String role) {
        String query = "UPDATE user SET name = '" + name + "', username = '" + username + "', password = '" + password +
                "', role = '" + role.toUpperCase() + "' WHERE id = " + id;

        return query;
    }

    public void insert(User user) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement statement = null;
        String query = createInsertQuery(user);
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "User:insert " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot create this user");
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }
    }

    public void deleteUser(String query) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement statement = null;
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "User:delete " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot delete this user");
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }
    }

    public User findUser(String query) {
        Connection connection = ConnectionFactory.getConnection();

        User user = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String role = resultSet.getString("role");
                if (role.equals("ADMINISTRATOR")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.ADMINISTRATOR);
                } else if (role.equals("MANAGER")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.MANAGER);
                } else {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.EMPLOYEE);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "User:find " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot find this user");
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
            return user;
        }
    }

    public List<User> findAllUsers() {
        Connection connection = ConnectionFactory.getConnection();

        List<User> userList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        System.out.println(query);

        try {
            User user = null;

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String role = resultSet.getString("role");
                if (role.equals("ADMINISTRATOR")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.ADMINISTRATOR);
                } else if (role.equals("MANAGER")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.MANAGER);
                } else {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.EMPLOYEE);
                }

                userList.add(user);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "User:find " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot view all userss");
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
            return userList;
        }
    }

    public List<User> findUsersByRole(String roleIn) {
        Connection connection = ConnectionFactory.getConnection();

        List<User> userList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindQueryByRole(roleIn);
        System.out.println(query);

        try {
            User user = null;

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String role = resultSet.getString("role");
                if (role.equals("ADMINISTRATOR")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.ADMINISTRATOR);
                } else if (role.equals("MANAGER")) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.MANAGER);
                } else {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), Role.EMPLOYEE);
                }

                userList.add(user);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "User:find " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
            return userList;
        }
    }

    public void updateUser(int id, String name, String username, String password, String role) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(id, name, username, password, role);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "DAO:update " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot update this user");
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


}

