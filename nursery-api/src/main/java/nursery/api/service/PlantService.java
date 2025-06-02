package nursery.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nursery.api.controller.model.PlantData;
import nursery.api.dao.PlantDao;
import nursery.api.entity.Plant;

import java.util.NoSuchElementException;

@Service
public class PlantService {

    @Autowired
    private PlantDao plantDao;

    public PlantData savePlant(PlantData data) {
        Plant p = (data.getId() == null)
            ? new Plant()
            : plantDao.findById(data.getId())
                .orElseThrow(() ->
                    new NoSuchElementException("No Plant found with id=" + data.getId()));

        p.setName(data.getName());
        p.setDescription(data.getDescription());
        p.setPlantType(data.getPlantType());
       

        Plant saved = plantDao.save(p);
        return new PlantData(saved);
    }
}