package api;

import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PetsEndpointTest extends BaseApi {

    private final static Integer PET_ID = 1234567;
    private final static String NAME = "Vincent";

    @BeforeClass
    public void dataSetup() {
        addPetForTest(PET_ID, NAME);
    }

    @DataProvider(name = "statusProvider")
    public static Object[][] statusProvider() {
        return new Object[][]{
                {Pet.Status.AVAILABLE.getLabel()},
                {Pet.Status.PENDING.getLabel()},
                {Pet.Status.SOLD.getLabel()}
        };
    }

    @Test(dataProvider = "statusProvider")
    public void checkFindPetsByStatus(String status) {
        given()
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/listOfPetsSchema.json"));
    }

    @Test
    public void addNewPetToTheStore() {
        Pet petToAdd = getPetPayload(PET_ID, NAME);

        given()
                .contentType(ContentType.JSON)
                .body(petToAdd)
                .when()
                .post("/pet")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/singlePetSchema.json"))
                .body("name", equalTo(NAME));
    }

    @Test(priority = 1)
    public void checkFindPetsByPetId() {
        given()
                .when()
                .pathParam("petId", PET_ID)
                .get("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/singlePetSchema.json"))
                .body("name", equalTo(NAME));
    }

    @Test(priority = 2)
    public void updatePetDetails() {
        String modifiedName = "Alpha";
        Pet petToUpdate = getPetPayload(PET_ID, modifiedName);

        given()
                .contentType(ContentType.JSON)
                .body(petToUpdate)
                .when()
                .put("/pet")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/singlePetSchema.json"))
                .body("name", equalTo(modifiedName));
    }

    @Test(priority = 3)
    public void deleteExistingPet() {
        given()
                .when()
                .pathParam("petId", PET_ID)
                .delete("/pet/{petId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(PET_ID.toString()));
    }

    private void addPetForTest(Integer petId, String name) {
        Pet petToAdd = getPetPayload(petId, name);
        given()
                .contentType(ContentType.JSON)
                .body(petToAdd)
                .when()
                .post("/pet")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/singlePetSchema.json"))
                .body("name", equalTo(name));
    }

    private Pet getPetPayload(Integer id, String name) {
        return new Pet.PetBuilder()
                .id(id)
                .category(new IdNamePair.IdNamePairBuilder().id(1).name("Small dog").build())
                .name(name)
                .photoUrls(Collections.singletonList("noUrl"))
                .tags(Collections.singletonList(new IdNamePair.IdNamePairBuilder().id(123).name("Small dog").build()))
                .status(Pet.Status.AVAILABLE.getLabel())
                .build();
    }

}
