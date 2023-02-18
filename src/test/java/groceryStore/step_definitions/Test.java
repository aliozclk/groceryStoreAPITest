package groceryStore.step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import groceryStore.pojos.request.CreateOrderRequest;
import groceryStore.pojos.response.AddAnItemToCartResponse;
import groceryStore.pojos.response.CreateCartResponse;

import groceryStore.pojos.response.CreateOrderResponse;
import groceryStore.pojos.response.ProductItemsResponse;
import groceryStore.utilities.APIResources;
import groceryStore.utilities.APIUtils;
import groceryStore.utilities.TestDataBuild;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apiguardian.api.API;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class Test  {

    public static void main(String[] args) throws JsonProcessingException {

        //@When("the status {string}")
        //public void the_status(String state) {
        APIUtils utils = new APIUtils();
        TestDataBuild testDataBuild = new TestDataBuild();


        Response response;
        APIResources resources = new APIResources();
        APIUtils.setBaseURI("https://simple-grocery-store-api.glitch.me");
        response = given().get(resources.getStatusAPI());
        JsonPath js = new JsonPath(response.asString());
        String status = js.get("status");
        System.out.println(status);
        Assert.assertEquals(status, "UP");

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        RequestSpecification reqStatus = given().log().all().spec(req);
        response = reqStatus.when().get(resources.getGetAllProductsAPI())
                .then().extract().response();

        ObjectMapper objectMapper = utils.getObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        String responseStr = response.asString();
        ProductItemsResponse[] productsResponse = objectMapper.readValue(responseStr,ProductItemsResponse[].class);
        System.out.println(productsResponse[1].getName());

        int productId = productsResponse[1].getId();


        //Create a cart

        response = given(req).log().all().post(resources.getCreateCartAPI())
                .then().extract().response();

        responseStr = response.asString();
        CreateCartResponse cartResponse = objectMapper.readValue(responseStr,CreateCartResponse.class);
        System.out.println(cartResponse.getCartId());

        String cartId = cartResponse.getCartId();
        resources.setCartId(cartId);
        resources.setAddItemToCartAPI(cartId);


        //Add an item to cart
        RequestSpecification addAnItem = given().log().all().spec(req).body(testDataBuild.addAnItemPayload(productId,5));
        responseStr = addAnItem.when().post(resources.getAddItemToCartAPI()).then()
                .extract().response().asString();
        AddAnItemToCartResponse addAnItemToCartResponse = objectMapper.readValue(responseStr, AddAnItemToCartResponse.class);
        System.out.println(addAnItemToCartResponse.getItemId());
        int itemId = addAnItemToCartResponse.getItemId();


        resources.setItemId(String.valueOf(itemId));


        //modify an item in the cart with determined value
        resources.setModifyAnItem(String.valueOf(itemId));
        RequestSpecification modifyItem = given().log().all().spec(req).body(testDataBuild.modifyAnItemPayload(5));
        int actualStatusCode = modifyItem.when().patch(resources.getModifyAnItem()).then()
                .extract().response().getStatusCode();
        Assert.assertEquals(204,actualStatusCode);

        //Get Bearer Token
        String clientName = "client4";
        String clientEmail = "client4@mail.com";

        RequestSpecification getToken = given().log().all().spec(req)
                .body(testDataBuild.createTokenPayload(clientName,clientEmail));
        response = getToken.when().post(resources.getGetTokenAPI()).then()
                .extract().response();
        JsonPath jsonPath = APIUtils.responseToJsonPath(response);
        String token = jsonPath.get("accessToken");
        System.out.println(token);

        //Create an order


        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .customerName(clientName)
                .cartId(cartId)
                .build();

        RequestSpecification createOrder = given().log().all().spec(req)
                .headers("Authorization",token)
                .body(testDataBuild.createOrderPayload(cartId,clientName));
        responseStr = createOrder.when().post(resources.getCreateAnOrderAPI()).then()
                .extract().response().asString();
        CreateOrderResponse createOrderResponse = objectMapper.readValue(responseStr, CreateOrderResponse.class);
        Assert.assertTrue(createOrderResponse.isCreated());
    }
}
