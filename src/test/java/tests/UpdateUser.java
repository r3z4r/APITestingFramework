package tests;

import helpers.UserHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Common;

public class UpdateUser {

    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test
    public void testUpdateUser(){
        String name = Common.RandomTextGenerator(8);
        Response response = userHelper.UpdateUser(11,name);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User updated");
        String updatedName = response.jsonPath().getString("data.name");
        Assert.assertEquals(updatedName, name , "Name updated");
    }
}
