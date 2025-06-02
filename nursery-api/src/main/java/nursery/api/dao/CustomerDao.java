package nursery.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nursery.api.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
