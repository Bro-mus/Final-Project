package nursery.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import nursery.api.controller.model.PlantData;
import nursery.api.service.PlantService;

import java.util.List;

@RestController
@RequestMapping("/nursery_api/plants")
@Slf4j
public class PlantController {

    @Autowired
    private PlantService plantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlantData createPlant(@RequestBody PlantData data) {
        log.info("Creating Plant: {}", data);
        return plantService.savePlant(data);
    }
    
    @GetMapping
    public List<PlantData> getAllPlants() {
        return plantService.findAllPlants();  // implement findAllPlants() if missing
    }

    @GetMapping("/{id}")
    public PlantData getPlantById(@PathVariable Long id) {
        return plantService.findPlantById(id);
    }

    @PutMapping("/{id}")
    public PlantData updatePlant(
            @PathVariable Long id,
            @RequestBody PlantData data) {
        data.setId(id);
        log.info("Updating Plant {}: {}", id, data);
        return plantService.savePlant(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
    }
}