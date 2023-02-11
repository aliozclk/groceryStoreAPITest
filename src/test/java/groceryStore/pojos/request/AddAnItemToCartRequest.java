package groceryStore.pojos.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddAnItemToCartRequest{
	private int quantity;
	private int productId;
}