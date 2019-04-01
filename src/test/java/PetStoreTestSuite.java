import com.google.gson.Gson;
import core.be.PetStoreApi;
import core.be.dto.Category;
import core.be.dto.PetModel;
import core.be.dto.Tag;
import io.restassured.response.Response;
import org.junit.Test;

public class PetStoreTestSuite {

    @Test
    public void checkAddNewPetApi() {
        final PetModel requestBody = new PetModel();
        final Tag[] tags = {
                new Tag(13, "MyTestTag")
        };
        requestBody.setId(1554137067);
        requestBody.setCategory(new Category(12, "MyTestCategory"));
        requestBody.setTags(tags);
        requestBody.setName("Hillel_Pet");
        requestBody.setPhotoUrls(new String[] {"file"});

        final Gson gson = new Gson();

        final String requestBodyString = gson.toJson(requestBody);

        final PetStoreApi petStoreApi = new PetStoreApi();
        final PetModel response = petStoreApi.addNewPet(requestBodyString);
    }
}
