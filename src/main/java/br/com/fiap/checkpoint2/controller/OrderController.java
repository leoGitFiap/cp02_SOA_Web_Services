// 37.

package br.com.fiap.checkpoint2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint2.model.OrderModel;
import br.com.fiap.checkpoint2.service.OrderService;

// 40.
@RequestMapping ("/orders")
// 39.
@RestController
// 38.
public class OrderController {

    // 42.
    @Autowired 
    // 41.
    private OrderService orderService;

    // 44.
    @PostMapping
    // 43.
    public ResponseEntity<Object> createOrder(OrderModel order) {

        try {
            OrderModel orderModel = orderService.createOrder(order);
            return new ResponseEntity<>(orderModel, HttpStatus.CREATED);
        } 
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
