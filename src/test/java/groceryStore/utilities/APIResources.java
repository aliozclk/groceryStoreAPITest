package groceryStore.utilities;

import groceryStore.pojos.response.CreateCartResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResources {

    //elements that are needed in path
    private String cartId ;
    private String itemId;
    private String productId;
    private String token;

    //paths
    private String statusAPI = "/status";
    private String getAllProductsAPI = "/products";
    private String addItem ;




    private String getSpecifiedProductAPI;
    private String createCartAPI = "/carts";
    private String addItemToCartAPI ;
    private String modifyAnItem ;
    private String getTokenAPI = "/api-clients";
    private String createAnOrderAPI = "/orders";

    public void setGetSpecifiedProductAPI(String productId) {
        this.getSpecifiedProductAPI = "/products/"+ productId +":productId" ;
    }

    public void setAddItemToCartAPI(String cartId) {
        this.addItemToCartAPI = createCartAPI+"/" + cartId + "/items";
    }

    public void setModifyAnItem(String itemId) {
        this.modifyAnItem = addItemToCartAPI + "/" + itemId ;
    }

    public void setAddItem() {
        this.addItem = (getCreateCartAPI() +"/" + getCartId() +"/items");
    }
}
