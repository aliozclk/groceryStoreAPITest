package groceryStore.utilities;

import groceryStore.pojos.request.AddAnItemToCartRequest;
import groceryStore.pojos.request.CreateOrderRequest;
import groceryStore.pojos.request.CreateTokenRequest;
import groceryStore.pojos.request.DetermineQuantityRequest;

public class TestDataBuild {
    public AddAnItemToCartRequest addAnItemPayload(int productId, int quantity){
        AddAnItemToCartRequest request = AddAnItemToCartRequest.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        return request;
    }

    public DetermineQuantityRequest modifyAnItemPayload(int quantity){
        DetermineQuantityRequest request = DetermineQuantityRequest.builder()
                .quantity(quantity)
                .build();

        return request;
    }

    public CreateOrderRequest createOrderPayload(String cartId,String customerName){
        CreateOrderRequest request = CreateOrderRequest.builder()
                .cartId(cartId)
                .customerName(customerName)
                .build();

        return request;
    }

    public CreateTokenRequest createTokenPayload(String clientName,String clientEmail){
        CreateTokenRequest request = CreateTokenRequest.builder()
                .clientName(clientName)
                .clientEmail(clientEmail)
                .build();

        return request;
    }


}
