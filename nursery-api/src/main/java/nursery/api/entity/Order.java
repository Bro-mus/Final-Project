package nursery.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;

    // Many-to-One with Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    
 // Replace the  ManyToMany with a OneToMany to OrderPlant. To include quantity of each plant in an order,
    // you replace a direct @ManyToMany with two @OneToMany mappings plus a join‐entity class.
    @OneToMany(
      mappedBy = "order",
      cascade = CascadeType.ALL,
      orphanRemoval = true
    )
    private List<OrderPlant> orderPlants = new ArrayList<>();
}



