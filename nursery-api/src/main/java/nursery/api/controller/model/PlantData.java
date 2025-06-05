package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nursery.api.entity.Plant;


@Data
@NoArgsConstructor
public class PlantData {
    private Long id;
    private String name;
    private String plantType;
    private Double price;
    private String description;
    private Integer inventory;
    
    //constructed from Plant.java */
    public PlantData(Plant p) {
        this.id          = p.getPlantId();
        this.name        = p.getName();
        this.plantType   = p.getPlantType();
        this.description = p.getDescription();   
        this.price       = p.getPrice();
        //add inventory for more useful order return that isnt null
        this.inventory   = p.getInventory();
    }
}