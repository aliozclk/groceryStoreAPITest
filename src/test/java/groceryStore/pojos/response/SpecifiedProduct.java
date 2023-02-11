package groceryStore.pojos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SpecifiedProduct {
	private int currentStock;
	private Object price;
	private String name;
	private boolean inStock;
	private int id;
	private String category;
	private String manufacturer;
}