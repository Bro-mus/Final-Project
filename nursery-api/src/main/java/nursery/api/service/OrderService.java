package nursery.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nursery.api.controller.model.OrderData;
import nursery.api.controller.model.OrderLineItemData;
import nursery.api.dao.CustomerDao;
import nursery.api.dao.OrderDao;
import nursery.api.dao.OrderPlantDao;
import nursery.api.dao.PlantDao;
import nursery.api.entity.Customer;
import nursery.api.entity.Order;
import nursery.api.entity.OrderPlant;
import nursery.api.entity.Plant;

@Service
//Each OrderPlant(op), sets  op.setOrder(o), op.setPlant(p), and set quantity. 
// Cascade=ALL means saving order also persists each OrderPlant.

public class OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PlantDao plantDao;
	@SuppressWarnings("unused")
	@Autowired
	private OrderPlantDao orderPlantDao;

	//constructor loops building each line item, wrap in plant data to get data  and pull id, name, price, etc
	public OrderData saveOrder(OrderData data) { 
		Order o = (data.getOrderId() == null) ? new Order() // if ID null, instantiate new order or throw exception
				: orderDao.findById(data.getOrderId())
						.orElseThrow(() -> new NoSuchElementException("No Order found with id=" + data.getOrderId()));

		// link to the customer, loop up ID and throw 404 if absent
		if (data.getCustomer() != null) {
			Long custId = data.getCustomer().getCustomerId();
			Customer c = customerDao.findById(custId)
					.orElseThrow(() -> new NoSuchElementException("No Customer found with id=" + custId));
			o.setCustomer(c); //parse info
		}

		// parse the order date
		o.setOrderDate(java.time.LocalDate.parse(data.getOrderDate()));

		// use clear method to remote existing order-plant lines or it may break updates
		o.getOrderPlants().clear();

		// For each DTO line‐item, its going to look up info and get the items
		for (OrderLineItemData dtoLine : data.getItems()) {
			Long plantId = dtoLine.getPlant().getId();
			Plant p = plantDao.findById(plantId)
					.orElseThrow(() -> new NoSuchElementException("No Plant found with id=" + plantId));

			OrderPlant op = new OrderPlant();
			op.setOrder(o);
			op.setPlant(p);
			op.setQuantity(dtoLine.getQuantity());

			o.getOrderPlants().add(op);
		}
		// order save
		Order saved = orderDao.save(o); //cascades to persist all rows
		// DTO return refresh
		return new OrderData(saved);

	}

	public List<OrderData> findAllOrders() {
		return orderDao.findAll().stream().map(OrderData::new).toList();
	}

	public OrderData findOrderById(Long id) {
		Order o = orderDao.findById(id).orElseThrow(() -> new NoSuchElementException("No Order found with id=" + id));
		return new OrderData(o);
	}

	public void deleteOrder(Long id) {
		if (!orderDao.existsById(id)) {
			throw new NoSuchElementException("No Order found with id=" + id);
		}
		orderDao.deleteById(id);
	}
}