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
    //adding inventory for orders
    private Integer inventory;
    
    @Column(name = "plant_type")
    private String plantType;  // e.g. forb, shrub, tree, etc
    
    // Technically still many to many between plant and roder after adding the join entity orderplant to add inventory/quantity in crud
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderPlant> orders = new ArrayList<>();
}