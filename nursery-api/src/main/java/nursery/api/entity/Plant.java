package nursery.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Plant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    private String name;
    private String description;
    private Double price;

    @Column(name = "plant_type")
    private String plantType;  // e.g. "Tree", "Shrub", "Annual"

    // Many-to-Many with Order
    @ManyToMany(mappedBy = "plants")
    private List<Order> orders = new ArrayList<>();
}