package groceryStore.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecifiedProduct {
	private int currentStock;
	private Object price;
	private String name;
	private boolean inStock;
	private int id;
	private String category;
	private String manufacturer;
}