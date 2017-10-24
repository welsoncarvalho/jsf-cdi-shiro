package com.test.jsf.cdi.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.jsf.cdi.model.Order;
import com.test.jsf.cdi.service.OrderService;

@Named
@RequestScoped
public class OrderController {

    @Inject
    private OrderService orderService;
    
    private Order order;
    
    @PostConstruct
    private void init() {
        this.order = new Order();
    }
    
    public void send() {
        
        Integer numOrder = orderService.send(getOrder());
        
        FacesContext.getCurrentInstance().addMessage("", 
                new FacesMessage(String.format("Hello %s, your order was success send %s", 
                        this.order.getName(), String.valueOf(numOrder))));  
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
}
