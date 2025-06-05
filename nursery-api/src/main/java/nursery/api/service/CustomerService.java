package nursery.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nursery.api.controller.model.CustomerData;
import nursery.api.dao.CustomerDao;
import nursery.api.entity.Customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public CustomerData saveCustomer(CustomerData data) {
        Customer c = (data.getCustomerId() == null)
            ? new Customer()
            : customerDao.findById(data.getCustomerId())
                .orElseThrow(() ->
                    new NoSuchElementException("No Customer found with id=" + data.getCustomerId()));

        c.setName(data.getName());
        c.setEmail(data.getEmail());

        Customer saved = customerDao.save(c);
        return new CustomerData(saved);
    }
    
    public List<CustomerData> findAllCustomers() {
        return customerDao.findAll()
            .stream()
            .map(CustomerData::new)
            .collect(Collectors.toList());
    }
   
    public CustomerData findCustomerById(Long id) {
        Customer c = customerDao.findById(id)
            .orElseThrow(() ->
                new NoSuchElementException("No Customer found with id=" + id));
        return new CustomerData(c);
    }

    public void deleteCustomer(Long id) {
        if (!customerDao.existsById(id)) {
            throw new NoSuchElementException("No Customer found with id=" + id);
        }
        customerDao.deleteById(id);
    }

}