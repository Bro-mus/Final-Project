package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nursery.api.entity.Plant;


@Data
@NoArgsConstructor
public class PlantData {
    private Long id;
    private String name;
    private String description;
    private String plantType;
    private Integer inventory;

    /** Construct from JPA Plant */
    public PlantData(Plant p) {
        this.id        = p.getPlantId();
        this.name      = p.getName();
        this.description= p.getDescription();
        this.plantType = p.getPlantType();
 
    }
}