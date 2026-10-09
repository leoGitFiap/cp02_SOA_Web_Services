// 37.

package br.com.fiap.checkpoint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint2.model.OrderModel;
import br.com.fiap.checkpoint2.service.OrderService;
import jakarta.persistence.EntityNotFoundException;

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

    // 46.
    @GetMapping
    // 45.
    public List<OrderModel> readOrders() {
        return orderService.readAllOrders();
    }

    // 48.
    @GetMapping("/{code}")
    // 47.
    public ResponseEntity<Object> getOrders(@PathVariable Long id) {
        try {
            OrderModel order =  orderService.readOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
