package nursery.api.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nursery.api.controller.model.OrderData;
import nursery.api.controller.model.PlantData;
import nursery.api.dao.CustomerDao;
import nursery.api.dao.OrderDao;
import nursery.api.dao.PlantDao;
import nursery.api.entity.Customer;
import nursery.api.entity.Order;
import nursery.api.entity.Plant;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PlantDao plantDao;

    public OrderData saveOrder(OrderData data) {
        Order o = (data.getOrderId() == null)
            ? new Order()
            : orderDao.findById(data.getOrderId())
                .orElseThrow(() ->
                    new NoSuchElementException("No Order found with id=" + data.getOrderId()));

        if (data.getCustomer() != null) {
            Long custId = data.getCustomer().getCustomerId();
            Customer c = customerDao.findById(custId)
                .orElseThrow(() ->
                    new NoSuchElementException("No Customer found with id=" + custId));
            o.setCustomer(c);
        }

        // link plants
        o.getPlants().clear();
        for (PlantData pd : data.getPlants()) {
            Plant p = plantDao.findById(pd.getId())
                .orElseThrow(() ->
                    new NoSuchElementException("No Plant found with id=" + pd.getId()));
            o.getPlants().add(p);
        }

        Order saved = orderDao.save(o);
        return new OrderData(saved);
    }
}