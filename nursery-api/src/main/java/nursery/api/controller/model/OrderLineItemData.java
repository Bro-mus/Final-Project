package nursery.api.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLineItemData {
	/* 
	Suggestion to improve/feedback -- customer can get specific inventory amounts if we add DTO for order inventory
	use only plant ID for simplicity but response include all plant data quantity for how many of =plant id
	 Use a List in order data 
 */
	 private PlantData plant;
	 private Integer quantity;

}
