package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nursery.api.entity.Customer;


@Data
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    private String name;
    private String email;

    public CustomerData(Customer c) {
        this.customerId        = c.getCustomerId();
        this.name = c.getName();
        this.email     = c.getEmail();
    }
}