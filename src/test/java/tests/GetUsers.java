package tests;

import helpers.UserHelper;
import io.restassured.response.Response;
import model.Datum;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class GetUsers {
    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test
    public void testGetAllUsers(){
        List<Datum> usersList = userHelper.getUsers();
        Assert.assertNotNull(usersList,"users are coming");
    }

    @Test
    public void testGetSingleUser(){
        List<Datum> usersList = userHelper.getUsers();
        Response response = userHelper.getSingleUser(1);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User retrieved");
        String id = response.jsonPath().getString("data.id");
        Assert.assertEquals(id, "1" , "retrieved");
    }
}
