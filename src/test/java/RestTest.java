import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.ProductPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestTest {


    @Test
    public void testPost() {

        Specifications.installSpecification(Specifications.requestSpecification("https://qualit.appline.ru"), Specifications.responseSpecification(200));

        RestAssured.defaultParser = Parser.JSON;

        List<ProductPojo> product = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/food")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("ProductPojo", ProductPojo.class);


        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Банан\",\n" +
                        "    \"type\": \"VEGETABLE\",\n" +
                        "    \"exotic\": false\n" +
                        "  }")
                .basePath("/api/food")
                .when()
                .post();
        response.then()
                .assertThat()
                .statusCode(200)
                .log().all();
        Map<String, String> cookies = response.then()
                .extract()
                .cookies();

        given()
                .cookies(cookies)
                .when()
                .get("/api/food");

        response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body();
        String check = response.getBody().asPrettyString();
        check.contains("Банан");
    }
}

