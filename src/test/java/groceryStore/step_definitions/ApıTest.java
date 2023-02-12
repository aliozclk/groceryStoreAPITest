package groceryStore.step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import groceryStore.pojos.request.CreateOrderRequest;
import groceryStore.pojos.response.*;
import groceryStore.utilities.APIResources;
import groceryStore.utilities.APIUtils;
import groceryStore.utilities.TestDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApıTest extends APIUtils {


    private APIResources resources = new APIResources();
    private TestDataBuild testDataBuild = new TestDataBuild();
    private Response response;
    ObjectMapper objectMapper = getObjectMapper();
    private int productId;
    private String cartId;
    private int itemId;
    private String customerName;
    private String token;


    RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
            .setContentType(ContentType.JSON).build();


    
    @Given("the status {string}")
    public void the_status(String state) {
        RequestSpecification reqStatus = given().log().all().spec(req);
        response = reqStatus.when().get(resources.getStatusAPI())
                .then().extract().response();
        JsonPath js = new JsonPath(response.asString());
        String status = js.get("status");
        System.out.println(status);
        Assert.assertEquals(status, state);

    }
    @When("the user gets all products and print {string}'s id")
    public void the_user_gets_all_products_with(String productName) throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        RequestSpecification reqProducts = given().log().all().spec(req);
        response = reqProducts.when().get(resources.getGetAllProductsAPI())
                .then().extract().response();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        String responseStr = response.asString();
        ProductItemsResponse[] productsResponse = objectMapper.readValue(responseStr,ProductItemsResponse[].class);

        for (int i = 0; i < productsResponse.length; i++) {
            if(productsResponse[i].getName().equalsIgnoreCase(productName)){
                productId = productsResponse[i].getId();
                System.out.println(productId);
            }
        }

    }
    @Then("the user creates a cart with")
    public void the_user_creates_a_cart_with() throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        response = given(req).log().all().post(resources.getCreateCartAPI())
                .then().extract().response();

        String responseStr = response.asString();
        CreateCartResponse cartResponse = objectMapper.readValue(responseStr,CreateCartResponse.class);
        System.out.println(cartResponse.getCartId());

        cartId = cartResponse.getCartId();
        resources.setCartId(cartId);
        resources.setAddItemToCartAPI(cartId);
    }
    @Then("the user adds an item to cart with")
    public void the_user_adds_an_item_to_cart_with() throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        RequestSpecification addAnItem = given().log().all().spec(req).body(testDataBuild.addAnItemPayload(productId,5));
        String responseStr = addAnItem.when().post(resources.getAddItemToCartAPI()).then()
                .extract().response().asString();
        AddAnItemToCartResponse addAnItemToCartResponse = objectMapper.readValue(responseStr, AddAnItemToCartResponse.class);
        System.out.println(addAnItemToCartResponse.getItemId());
        itemId = addAnItemToCartResponse.getItemId();
    }
    @Then("the user modifies an item by determining quantity")
    public void the_user_modifies_an_item_by_determining_quantity() {
        // Write code here that turns the phrase above into concrete actions
        resources.setModifyAnItem(String.valueOf(itemId));
        RequestSpecification modifyItem = given().log().all().spec(req).body(testDataBuild.modifyAnItemPayload(5));
        int actualStatusCode = modifyItem.when().patch(resources.getModifyAnItem()).then()
                .extract().response().getStatusCode();
        Assert.assertEquals(204,actualStatusCode);

    }
    @Then("the user gets token with clientName {string} and clientEmail {string}")
    public void the_user_gets_token(String clientName, String clientEmail) {
        // Write code here that turns the phrase above into concrete actions
        RequestSpecification getToken = given().log().all().spec(req)
                .body(testDataBuild.createTokenPayload(clientName,clientEmail));
        response = getToken.when().post(resources.getGetTokenAPI()).then()
                .extract().response();
        JsonPath jsonPath = APIUtils.responseToJsonPath(response);
        token = jsonPath.get("accessToken");
        System.out.println(token);
        customerName = clientName;
    }
    @Then("the user creates an order")
    public void the_user_creates_an_order() throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .customerName(customerName)
                .cartId(cartId)
                .build();

        RequestSpecification createOrder = given().log().all().spec(req)
                .headers("Authorization",token)
                .body(testDataBuild.createOrderPayload(cartId,customerName));

        String responseStr = createOrder.when().post(resources.getCreateAnOrderAPI()).then()
                .extract().response().asString();
        CreateOrderResponse createOrderResponse = objectMapper.readValue(responseStr, CreateOrderResponse.class);
        Assert.assertTrue(createOrderResponse.isCreated());    }
}
