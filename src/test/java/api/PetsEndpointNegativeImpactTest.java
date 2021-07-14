package api;

import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PetsEndpointNegativeImpactTest extends BaseApi {

    @Test
    public void deleteNotExistingPet() {
        given()
                .when()
                .pathParam("petId", 91473741)
                .delete("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void deletePetUsingInvalidPetId() {
        given()
                .when()
                .pathParam("petId", "%")
                .delete("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", containsString("NumberFormatException"));
    }

    @Test
    public void addNewPetToTheStoreWithEmptyPayloadWithInvalidContentType() {
        given()
                .contentType(ContentType.TEXT)
                .body("")
                .when()
                .post("/pet")
                .then()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void getPetUsingInvalidPetId() {
        given()
                .when()
                .pathParam("petId", "wqbhqfbhebdddd#$%^&*(")
                .get("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", containsString("NumberFormatException"));
    }

    @Test
    public void getPetWithoutPetId() {
        given()
                .when()
                .get("/pet")
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void getPetUsingNotExistingPetId() {
        given()
                .when()
                .pathParam("petId", 112567876)
                .get("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", containsString("Pet not found"));
    }

    @Test
    public void updatePetWithoutPayload() {
        given()
                .when()
                .put("/pet")
                .then()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void updatePetWithoutCorruptedPayload() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"id\": 83678786786783267826786273862136387}")
                .when()
                .put("/pet")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body("message", containsString("something bad happened"));
    }

}
