package pages;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class ApiPage {

    private String baseUrl;

    //ApiKeyInformation text dosyasında bulunmaktadır.
    private static final String API_KEY = "";

    private ApiPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static ApiPageBuilder builder() {
        return new ApiPageBuilder();
    }

    public Response sendGetRequest(String endpoint) {
        return RestAssured.given().baseUri(baseUrl).get(endpoint);
    }

    public Response searchResponse(String searchTerm) {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam("apikey", API_KEY)
                .addQueryParam("s", searchTerm)
                .build();
        System.out.println("requestSpec = " + requestSpec);

        return RestAssured.given().spec(requestSpec).get();
    }

    public Response keySearchResponse(String imdbId, String key, String type) {
        RequestSpecification requestSpecType = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam("apikey", API_KEY)
                .addQueryParam("i", imdbId)
                .addQueryParam(key, type)
                .build();

        return RestAssured.given().spec(requestSpecType).get();
    }

    //Buna bakk
    public Response idSearchResponse(String imdbId) {
        RequestSpecification requestSpecType = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam("apikey", API_KEY)
                .addQueryParam("i", imdbId)
                .addQueryParam("y", 2001)
                .build();

        return RestAssured.given().spec(requestSpecType).get();
    }

    public Response idandSearchResponse(String imdbId) {
        RequestSpecification requestSpecType = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam("apikey", API_KEY)
                .addQueryParam("i", imdbId)
                .build();

        return RestAssured.given().spec(requestSpecType).get();
    }


    public static class ApiPageBuilder {
        private String baseUrl;

        public ApiPageBuilder withBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public ApiPage build() {
            return new ApiPage(baseUrl);
        }
    }
}
