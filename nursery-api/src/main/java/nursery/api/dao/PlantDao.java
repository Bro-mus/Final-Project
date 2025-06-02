package nursery.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nursery.api.entity.Plant;

public interface PlantDao extends JpaRepository<Plant, Long> {

}
