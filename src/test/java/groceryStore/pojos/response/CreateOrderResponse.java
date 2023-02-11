package groceryStore.pojos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderResponse{
	private String orderId;
	private boolean created;
}