package groceryStore.pojos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAnItemToCartRequest {
	private int productId;
	private int quantity;


}