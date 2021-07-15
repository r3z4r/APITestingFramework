package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Datum;
import model.Users;
import utils.ConfigManager;

import java.util.List;

public class UserHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getString("base_url");

    public UserHelper() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Datum> getUsers() {
        Response response = RestAssured.given().log().all().contentType(ContentType.JSON).get(Endpoints.GET_USERS).andReturn();
        Users users = response.as(new TypeReference<Users>(){}.getType());

        return users.getData();
    }
}
