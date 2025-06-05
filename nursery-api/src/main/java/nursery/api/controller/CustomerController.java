package nursery.api.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import nursery.api.controller.model.CustomerData;
import nursery.api.service.CustomerService;
import java.util.List;
@RestController
@RequestMapping("/nursery_api/customers")
@Slf4j

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerData createCustomer(@RequestBody CustomerData data) {
        log.info("Creating Customer: {}", data);
        return customerService.saveCustomer(data);
    }

    @GetMapping
    public List<CustomerData> getAllCustomers() {
        return customerService
                .findAllCustomers();  
               
    }
    @GetMapping("/{id}")
    public CustomerData getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerData updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerData data) {
        data.setCustomerId(id);
        log.info("Updating Customer {}: {}", id, data);
        return customerService.saveCustomer(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}