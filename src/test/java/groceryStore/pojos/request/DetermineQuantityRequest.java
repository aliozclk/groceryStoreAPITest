package groceryStore.pojos.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetermineQuantityRequest{
	private int quantity;
}