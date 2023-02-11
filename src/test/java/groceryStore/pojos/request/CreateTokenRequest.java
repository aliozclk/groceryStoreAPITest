package groceryStore.pojos.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateTokenRequest{
	private String clientName;
	private String clientEmail;
}