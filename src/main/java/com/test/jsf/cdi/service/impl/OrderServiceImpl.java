package com.test.jsf.cdi.service.impl;

import javax.ejb.Stateless;

import com.test.jsf.cdi.model.Order;
import com.test.jsf.cdi.service.OrderService;

@Stateless
public class OrderServiceImpl implements OrderService {

    @Override
    public Integer send(Order order) {
        System.out.println("save order");
        return 1;
    }

}
