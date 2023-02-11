package groceryStore.pojos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductItemsResponse {
	private String name;
	private boolean inStock;
	private int id;
	private String category;
}
