package com.projectdb.dao.impl;

import com.projectdb.dao.ProductDAO;
import com.projectdb.domain.Product;
import com.projectdb.utils.ConnectionUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final Logger log = Logger.getLogger(ProductDAOImpl.class);
    private static final String readAll = "select * from product";
    private static final String create = "insert into product(name, description, price) values (?,?,?)";
    private static final String update = "update product set name=?, description=?, price=? where id =?";
    private static final String readById = "select * from product where id = ?";
    private static final String deleteById = "delete from product where id = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDAOImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public Product create(Product product) {
        try {
            preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            log.error(e);
        }
        return product;
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(readById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            String desc = resultSet.getString("description");
            Integer price = resultSet.getInt("price");
            product = new Product(name,desc,price);
        } catch (SQLException e) {
            log.error(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        try {
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return product;
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
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(readAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String desc = resultSet.getString("description");
                Integer price = resultSet.getInt("price");
                Integer id = resultSet.getInt("id");
                products.add(new Product(id,name,desc,price));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return products;
    }
}
