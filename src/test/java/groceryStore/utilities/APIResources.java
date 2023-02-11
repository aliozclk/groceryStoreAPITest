package groceryStore.utilities;

import lombok.Data;

@Data
public class APIResources {

    //elements that are needed in path
    private String cartId;
    private String itemId;
    private String productId;

    //paths
    private String statusAPI = "/status";
    private String getAllProductsAPI = "/products";
    private String getSpecifiedProductAPI = "/products/"+ productId +":productId" ;
    private String createCartAPI = "/carts";
    private String addItemToCartAPI = createCartAPI+"/"+cartId+"/items";
    private String modifyAnItem = addItemToCartAPI + "/" + itemId ;
    private String getTokenAPI = "/api-clients";
    private String createAnOrderAPI = "/orders";

}
