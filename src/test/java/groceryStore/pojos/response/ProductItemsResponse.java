package groceryStore.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemsResponse {
	private String name;
	private boolean inStock;
	private int id;
	private String category;
}
