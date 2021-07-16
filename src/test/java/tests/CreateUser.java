package tests;

import helpers.UserHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Common;

import java.util.List;

public class CreateUser {

    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test
    public void testCreateUser(){
        String randomMail = Common.RandomTextGenerator(8)+"@testmail.com";
        Response response = userHelper.CreateUser(randomMail);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "User Created");
        String id = response.jsonPath().getString("data.id");
        Assert.assertNotNull(id , "response id is not null");
    }

}
