package groceryStore.pojos.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderRequest{
	private String cartId;
	private String customerName;
}