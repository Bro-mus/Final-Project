package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nursery.api.entity.Customer;
import nursery.api.entity.Order;
import nursery.api.entity.OrderPlant;
import nursery.api.entity.Plant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderData {
    private Long orderId;
    private String orderDate;          
    private CustomerData customer;
    
    //private Set<PlantData> plants = new HashSet<>();  CHANGING  to OrderInventoryData list
    
    private List<OrderLineItemData> items = new ArrayList<>();
   
    public OrderData(Order o) {
        this.orderId = o.getOrderId();
        this.orderDate = o.getOrderDate().toString();

        Customer c = o.getCustomer();
        if (c != null) {
            this.customer = new CustomerData(c);
        }

       // for (Plant p : o.getPlants()) {  -- DITCH and use new for loop to get "line items"
       //     this.plants.add(new PlantData(p));
        
        //build line item with a for loop
        
        for (OrderPlant op : o.getOrderPlants()) {
            OrderLineItemData dtoItem = new OrderLineItemData();
            dtoItem.setPlant(new PlantData(op.getPlant()));
            dtoItem.setQuantity(op.getQuantity());
            this.items.add(dtoItem);
        }
    }
}