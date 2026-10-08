// 26.

package br.com.fiap.checkpoint2.service;

import br.com.fiap.checkpoint2.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import br.com.fiap.checkpoint2.model.OrderModel;

// 27.
public class OrderService {

    // 28.
    private OrderRepository orderRepository;

    // 29.
    public OrderModel createOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    // 30.
    public List<OrderModel> readAllOrders() {
        return orderRepository.findAll();
    }

    // 31.
    public OrderModel readOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));
    }

}
