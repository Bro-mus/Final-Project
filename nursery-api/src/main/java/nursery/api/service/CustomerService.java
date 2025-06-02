package nursery.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nursery.api.controller.model.CustomerData;
import nursery.api.dao.CustomerDao;
import nursery.api.entity.Customer;

import java.util.NoSuchElementException;

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
}