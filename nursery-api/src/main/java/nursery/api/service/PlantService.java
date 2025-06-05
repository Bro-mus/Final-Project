package nursery.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nursery.api.controller.model.PlantData;
import nursery.api.dao.PlantDao;
import nursery.api.entity.Plant;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PlantService {

    @Autowired
    private PlantDao plantDao;

    //create or update
    public PlantData savePlant(PlantData data) {
        Plant p = (data.getId() == null)
            ? new Plant()
            : plantDao.findById(data.getId())
                .orElseThrow(() ->
                    new NoSuchElementException("No Plant found with id=" + data.getId()));

        p.setName(data.getName());
        p.setPlantType(data.getPlantType());
        
        //add price
        p.setPrice(data.getPrice());
        
        p.setDescription(data.getDescription());
        
        //add inventory
        p.setInventory(data.getInventory());

        Plant saved = plantDao.save(p);
        return new PlantData(saved);
    }
    public List<PlantData> findAllPlants() {
        return plantDao.findAll()
            .stream()
            .map(PlantData::new)
            .collect(Collectors.toList());
    }

    public PlantData findPlantById(Long id) {
        Plant p = plantDao.findById(id)
            .orElseThrow(() ->
                new NoSuchElementException("No Plant found with id=" + id));
        return new PlantData(p);
    }

    public void deletePlant(Long id) {
        if (!plantDao.existsById(id)) {
            throw new NoSuchElementException("No Plant found with id=" + id);
        }
        plantDao.deleteById(id);
    }
    
    
}