package nursery.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_plant")
@Data
@NoArgsConstructor
@AllArgsConstructor

/* This solves the issue with null inventory in orders. Join Entity between order and plant creates
 * the ability for customer to specify quantity in order from x inventory
 * creating join entity still technically preserves many to many relationship between order and plant
 */
public class OrderPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // "Many OrderPlant rows belong to one Order"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // "Many OrderPlant rows refer to one Plant"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    // quantity of x plant in y order
    private Integer quantity;
}

