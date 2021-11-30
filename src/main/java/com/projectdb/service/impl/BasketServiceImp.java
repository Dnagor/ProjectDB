package com.projectdb.service.impl;

import com.projectdb.dao.BasketDAO;
import com.projectdb.dao.impl.BasketDAOImpl;
import com.projectdb.domain.Basket;
import com.projectdb.service.BasketService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BasketServiceImp implements BasketService {
    private static final Logger log = Logger.getLogger(BasketDAOImpl.class);
    private static BasketService basketServiceImp;
    BasketDAO basketDAO;

    public static BasketService getBasketService(){
        if (basketServiceImp == null){
            basketServiceImp = new BasketServiceImp();
        }
        return basketServiceImp;
    }
    private BasketServiceImp() {
        try {
            basketDAO = new BasketDAOImpl() {
            };
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
