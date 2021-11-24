package com.projectdb.service.impl;

import com.projectdb.dao.UserDAO;
import com.projectdb.dao.impl.UserDAOImpl;
import com.projectdb.domain.User;
import com.projectdb.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDAO userDAO;

    public UserServiceImpl() {
        try {
            userDAO = new UserDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User create(User user) {
        return userDAO.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDAO.read(id);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDAO.readAll();
    }
}
