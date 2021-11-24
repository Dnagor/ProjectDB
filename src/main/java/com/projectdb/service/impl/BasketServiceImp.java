package com.projectdb.service.impl;

import com.projectdb.dao.BasketDAO;
import com.projectdb.dao.impl.BasketDAOImpl;
import com.projectdb.domain.Basket;
import com.projectdb.service.BasketService;

import java.sql.SQLException;
import java.util.List;

public class BasketServiceImp implements BasketService {
    BasketDAO basketDAO;

    public BasketServiceImp() {
        try {
            basketDAO = new BasketDAOImpl() {
            };
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
    public Basket create(Basket basket) {
        return basketDAO.create(basket);
    }

    @Override
    public Basket read(Integer id) {
        return basketDAO.read(id);
    }

    @Override
    public Basket update(Basket basket) {
        return basketDAO.update(basket);
    }

    @Override
    public void delete(Integer id) {
        basketDAO.delete(id);
    }

    @Override
    public List<Basket> readAll() {
        return basketDAO.readAll();
    }
}
