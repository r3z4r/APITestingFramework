package tests;

import helpers.UserHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CreateUser {

    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test
    public void testCreateUser(){
        Response response = userHelper.CreateUser();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "User Created");
        System.out.println(response);
    }

}
