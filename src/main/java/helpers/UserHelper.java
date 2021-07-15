package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Datum;
import model.Users;
import utils.Common;
import utils.ConfigManager;
import utils.JsonMapper;

import java.util.List;

public class UserHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
    private static final String ACCESS_TOKEN = ConfigManager.getInstance().getString("access_token");

    public UserHelper() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Datum> getUsers() {
        Response response = RestAssured.given().log().all().contentType(ContentType.JSON).get(Endpoints.GET_USERS).andReturn();
        Users users = response.as(new TypeReference<Users>(){}.getType());

        return users.getData();
    }

    public Response CreateUser(){
        Users testData = JsonMapper.getTestData();
        Datum user = testData.getData().get(0);
        user.setEmail(Common.RandomTextGenerator(8)+"@testmail.com");
        Response response = RestAssured.given()
                .auth()
                .oauth2(ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .body(user)
                .post(Endpoints.CREATE_USER)
                .andReturn();
        return response;
    }
}
