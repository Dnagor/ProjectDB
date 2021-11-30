package com.projectdb.service.impl;

import com.projectdb.dao.UserDAO;
import com.projectdb.dao.impl.UserDAOImpl;
import com.projectdb.domain.User;
import com.projectdb.service.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    private static UserService userServiceImpl;
    UserDAO userDAO;


    public static UserService getUserService() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
            return userServiceImpl;
    }

    private UserServiceImpl() {
        try {
            userDAO = new UserDAOImpl();
        } catch (SQLException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
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

    @Override
    public User readUserByEmail(String email) {
        return userDAO.readUserByEmail(email);
    }
}
