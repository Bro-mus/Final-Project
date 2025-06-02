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

    // Many-to-Many with Plant
    @ManyToMany
    @JoinTable(
      name = "plant_orders",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "plant_id")
    )
    private List<Plant> plants = new ArrayList<>();
}


