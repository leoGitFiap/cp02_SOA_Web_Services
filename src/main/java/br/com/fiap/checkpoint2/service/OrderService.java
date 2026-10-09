// 26.

package br.com.fiap.checkpoint2.service;

// 36.
import br.com.fiap.checkpoint2.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.fiap.checkpoint2.model.OrderModel;

// 34.
@Service
// 27.
public class OrderService {

    // 35.
    @Autowired
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

    // 32.
    public OrderModel updateOrder(Long id, OrderModel order) {
    return orderRepository.findById(id)
            .map(existingOrder -> {
                existingOrder.setClientName(order.getClientName());
                existingOrder.setTotalValue(order.getTotalValue());

                return orderRepository.save(existingOrder);
            })
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    // 33.
    public void deleteOrderById(Long id) {
        try {
            orderRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Pedido não encontrado com o ID: " + id, 1);
        }
    }

}
