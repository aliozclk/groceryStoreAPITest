package groceryStore.pojos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusResponse {
	private String status;
}