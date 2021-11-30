package com.projectdb.dao.impl;

import com.projectdb.dao.UserDAO;
import com.projectdb.domain.User;
import com.projectdb.utils.ConnectionUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private static final String readAll = "select * from user";
    private static final String create = "insert into user(email, firstName, lastName, role, password) values (?,?,?,?,?)";
    private static final String update = "update user set email=?, firstName=?, LastName=?, role=?, password=? where id =?";
    private static final String readById = "select * from user where id = ?";
    private static final String readByEmail = "select * from user where email = ?";
    private static final String deleteById = "delete from user where id = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDAOImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(readById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            user = new User(email, firstName, lastName, role, password);
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(readAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                Integer id = resultSet.getInt("id");
                userList.add(new User(id,email, firstName, lastName, role, password));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return userList;
    }

    @Override
    public User readUserByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(readByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String email1 = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            Integer id = resultSet.getInt("id");
            user = new User(email1, firstName, lastName, role, password);
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }
}
