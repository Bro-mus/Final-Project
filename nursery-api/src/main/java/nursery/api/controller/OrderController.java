
package nursery.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import nursery.api.controller.model.OrderData;
import nursery.api.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/nursery_api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //explicitly lays out CRUD operations via service layer
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderData createOrder(@RequestBody OrderData data) { 
        log.info("Creating Order: {}", data);
        return orderService.saveOrder(data);
    }

    @GetMapping
    public List<OrderData> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderData getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}")
    public OrderData updateOrder(
            @PathVariable Long id,
            @RequestBody OrderData data) {
        data.setOrderId(id);
        log.info("Updating Order {}: {}", id, data);
        return orderService.saveOrder(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}