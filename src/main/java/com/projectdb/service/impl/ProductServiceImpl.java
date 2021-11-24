package com.projectdb.service.impl;

import com.projectdb.dao.ProductDAO;
import com.projectdb.dao.UserDAO;
import com.projectdb.dao.impl.ProductDAOImpl;
import com.projectdb.domain.Product;
import com.projectdb.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO;

    public ProductServiceImpl() {
        try {
            productDAO = new ProductDAOImpl();
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
    public Product create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product read(Integer id) {
        return productDAO.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public void delete(Integer id) {
        productDAO.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDAO.readAll();
    }
}
