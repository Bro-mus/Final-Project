package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nursery.api.entity.Customer;
import nursery.api.entity.Order;
import nursery.api.entity.Plant;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderData {
    private Long orderId;
    private String orderDate;          
    private CustomerData customer;
    private Set<PlantData> plants = new HashSet<>();

   
    public OrderData(Order o) {
        this.orderId = o.getOrderId();
        this.orderDate = o.getOrderDate().toString();

        Customer c = o.getCustomer();
        if (c != null) {
            this.customer = new CustomerData(c);
        }

        for (Plant p : o.getPlants()) {
            this.plants.add(new PlantData(p));
        }
    }
}