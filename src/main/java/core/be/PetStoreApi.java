package core.be;

import core.be.dto.PetModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PetStoreApi extends AbstractApi {

    private static final String ADD_NEW_PET_PARTIAL_LINK = "/v2/pet";

    public PetModel addNewPet(final String petBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petBody)
                .post(ADD_NEW_PET_PARTIAL_LINK)
                .as(PetModel.class);
    }

    @Override
    protected String setUpBaseUrl() {
        return "https://petstore.swagger.io";
    }
}
