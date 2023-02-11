package groceryStore.pojos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCartResponse {
	private boolean created;
	private String cartId;
}