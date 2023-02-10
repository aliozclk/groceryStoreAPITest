package groceryStore.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIUtils {

    private static RequestSpecification requestSpec;
    private static Response response;

    public static void setBaseURI(String baseURI){
        RestAssured.baseURI = baseURI;
    }

    public static Response sendGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) {
        RestAssured.baseURI = url;
        return RestAssured.given().queryParams(queryParams).headers(headers).get();
    }

    public static Response sendPostRequest(String endpoint, String payload, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().contentType(ContentType.JSON).body(payload).headers(headers).post();
    }

}
