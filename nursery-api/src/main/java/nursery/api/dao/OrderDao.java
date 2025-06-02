package nursery.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import nursery.api.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

}
