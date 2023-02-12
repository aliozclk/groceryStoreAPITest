package groceryStore.pojos.response;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse{
	private String orderId;
	private boolean created;

}
