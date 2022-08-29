package adapters;

import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public abstract class BaseAdapter {
    protected final static String BASE_URL = System.getenv().getOrDefault("BASE_URL", PropertyReader.getProperty("qase.api_base_url"));
    private final static String ACCESS_TOKEN = System.getenv().getOrDefault("ACCESS_TOKEN", PropertyReader.getProperty("qase.access_token"));

    public String get(String endpoint, int statusCode) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .get(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();
    }

    public String post(String endpoint, int statusCode, String requestBody) {
         return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().all()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).
                extract().body().asString();
    }

}
