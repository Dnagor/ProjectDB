package com.projectdb.service.impl;

import com.projectdb.dao.ProductDAO;
import com.projectdb.dao.UserDAO;
import com.projectdb.dao.impl.ProductDAOImpl;
import com.projectdb.domain.Product;
import com.projectdb.service.ProductService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    private static ProductService productServiceImpl;
    ProductDAO productDAO;

    public static ProductService getProductService(){
        if (productServiceImpl == null){
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }

    private ProductServiceImpl() {
        try {
            productDAO = new ProductDAOImpl();
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
