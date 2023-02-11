package groceryStore.pojos.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductsResponse {
	private List<ProductItemsResponse> products;
}