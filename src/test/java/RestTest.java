import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojos.ProductPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .extract().body().jsonPath().getList("ProductPojo", ProductPojo.class);

        String productPost = given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Банан\",\n" +
                        "    \"type\": \"VEGETABLE\",\n" +
                        "    \"exotic\": false\n" +
                        "  }")
                .basePath("/api/food")
                .when()
                .log().all()
                .post()
                .then()
                .log().all()
                .extract().body().toString();

    }
}
