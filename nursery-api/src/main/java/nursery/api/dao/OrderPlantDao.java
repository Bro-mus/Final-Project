package nursery.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nursery.api.entity.OrderPlant;

public interface OrderPlantDao extends JpaRepository<OrderPlant, Long> {

}
