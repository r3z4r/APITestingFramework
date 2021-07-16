package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Datum;
import model.Users;
import utils.ConfigManager;
import utils.JsonMapper;

import java.util.List;

public class UserHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
    private static final String ACCESS_TOKEN = ConfigManager.getInstance().getString("access_token");

    private static Users testData = JsonMapper.getTestData();

    public UserHelper() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Datum> getUsers() {
        Response response = RestAssured.given().log().all().contentType(ContentType.JSON).get(Endpoints.GET_USERS).andReturn();
        Users users = response.as(new TypeReference<Users>(){}.getType());

        return users.getData();
    }

    public Response CreateUser(String mail){
        Datum user = testData.getData().get(0);
        user.setEmail(mail);
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

    public Response UpdateUser(int id, String name){
        Datum user = testData.getData().get(0);
        user.setName(name);
        Response response = RestAssured.given()
                .auth()
                .oauth2(ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .body(user)
                .patch(Endpoints.UPDATE_USER)
                .andReturn();
        return response;
    }

    public Response getSingleUser(int id){
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .get(Endpoints.GET_SINGLE_USER)
                .andReturn();

        return response;
    }
}
