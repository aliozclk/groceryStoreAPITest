package groceryStore.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.StringReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {

    private static RequestSpecification requestSpec;
    private static Response response;

    public static void setBaseURI(String baseURI){
        RestAssured.baseURI = baseURI;
    }

    public static Response sendGetRequest(String url) {
        return given().when().get(url);
    }

    public static Response sendGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) {
        RestAssured.baseURI = url;
        return RestAssured.given().queryParams(queryParams).headers(headers).get();
    }

    public static Response sendPostRequest(String endpoint, String payload, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().contentType(ContentType.JSON).body(payload).headers(headers).post();
    }

    public static Response sendPostRequest(String url, String payload) {
        return RestAssured.given().contentType(ContentType.JSON).body(payload).post(url);
    }

    public static Response sendPutRequest(String endpoint, String payload, Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().contentType(ContentType.JSON).body(payload).headers(headers).put();
    }

    public static Response sendDeleteRequest(String endpoint, Map<String, String> queryParams,
                                             Map<String, String> headers) {
        RestAssured.baseURI = endpoint;
        return RestAssured.given().queryParams(queryParams).headers(headers).delete();
    }

    public static String prettyPrintJson(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));
        jsonReader.setLenient(true);
        JsonElement jsonElement = gson.fromJson(jsonReader, JsonElement.class);
        return gson.toJson(jsonElement);
    }

    public static RequestSpecification getRequestSpecification() {
        requestSpec = given().contentType(ContentType.JSON);
        return requestSpec;
    }


}
