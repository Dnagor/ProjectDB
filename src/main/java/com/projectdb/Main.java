package com.projectdb;

import com.projectdb.domain.Basket;
import com.projectdb.domain.Product;
import com.projectdb.domain.User;
import com.projectdb.service.BasketService;
import com.projectdb.service.ProductService;
import com.projectdb.service.UserService;
import com.projectdb.service.impl.BasketServiceImp;
import com.projectdb.service.impl.ProductServiceImpl;
import com.projectdb.service.impl.UserServiceImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.create(new User("Hello@Hello","Hello","Hello","admin"));
        userService.delete(1);
        userService.readAll().forEach(System.out::println);
        ProductService productService = new ProductServiceImpl();
        productService.create(new Product("fff", "fff",11));
        productService.readAll().forEach(System.out::println);
        BasketService basketService = new BasketServiceImp();
//        basketService.create(new Basket(2,1,new Date(1000,1,1)));
        basketService.readAll().forEach(System.out::println);
        for (User user: userService.readAll()) {
            userService.delete(user.getId());
        }
        for (Product product: productService.readAll()) {
            productService.delete(product.getId());
        }
        for (Basket basket : basketService.readAll() ) {
            basketService.delete(basket.getId());
        }

    }
}
