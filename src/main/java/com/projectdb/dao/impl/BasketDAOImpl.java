package com.projectdb.dao.impl;

import com.projectdb.dao.BasketDAO;
import com.projectdb.domain.Basket;
import com.projectdb.service.BasketService;
import com.projectdb.utils.ConnectionUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketDAOImpl implements BasketDAO {
    private static final Logger log = Logger.getLogger(BasketDAOImpl.class);
    private static final String readAll = "select * from basket";
    private static final String create = "insert into basket(user_id, product_id, date) values (?,?,?)";
    private static final String readById = "select * from basket where id = ?";
    private static final String deleteById = "delete from basket where id = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public BasketDAOImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public Basket create(Basket basket) {
        try {
            preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, basket.getUserId());
            preparedStatement.setInt(2, basket.getProductId());
            preparedStatement.setTimestamp(3, basket.getPurchaseDate());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            basket.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            log.error(e);
        }
        return basket;
    }

    @Override
    public Basket read(Integer id) {
        Basket basket = null;
        try {
            preparedStatement = connection.prepareStatement(readById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer basketId = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            Integer productId = resultSet.getInt("product_id");
            Timestamp purchaseDate = resultSet.getTimestamp("date");
            basket = new Basket(basketId, userId, productId, purchaseDate);
        } catch (SQLException e) {
            log.error(e);
        }
        return basket;
    }


    @Override
    public Basket update(Basket basket) {
        throw new IllegalStateException("Basket cannot be updated");
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
    public List<Basket> readAll() {
        List<Basket> baskets = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(readAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer basketId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer productId = resultSet.getInt("product_id");
                Timestamp purchaseDate = resultSet.getTimestamp("date");
                baskets.add(new Basket(basketId, userId, productId, purchaseDate));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return baskets;
    }
}
