package tests;

import helpers.UserHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Common;

public class IntegrationTest {
    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test(groups = {"integration","positive"}, priority = 1)
    public void testCreateGetUpdateDeleteUser(){
        //creating a new user
        String randomMail = Common.RandomTextGenerator(10)+"@test.com";
        Response response = userHelper.CreateUser(randomMail);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "User Created");
        //verify user was created
        int id = Integer.parseInt(response.jsonPath().getString("data.id"));
        response = userHelper.getSingleUser(id);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User retrieved");
        String email = response.jsonPath().getString("data.email");
        Assert.assertEquals(email,randomMail,"Verify user creation");
        //updating user
        String randomName = Common.RandomTextGenerator(6);
        response = userHelper.UpdateUser(id, randomName);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User updated");
        //verify update
        String updatedName = response.jsonPath().getString("data.name");
        Assert.assertEquals(updatedName, randomName , "Verify user update");
        //Delete user
        response = userHelper.deleteUser(id);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT, "User deleted");
        //verify delete
        response = userHelper.getSingleUser(id);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Verify user deletion");
    }

    @Test(groups = {"integration","negative"}, priority = 2)
    public void testNegativeCreatingUserWithDuplicateMail(){
        //creating a new user
        String randomMail = Common.RandomTextGenerator(10)+"@test.com";
        Response response = userHelper.CreateUser(randomMail);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "User Created");
        int id = Integer.parseInt(response.jsonPath().getString("data.id"));

        //creating other user with same email
        response = userHelper.CreateUser(randomMail);
        //verify validation failure
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY, "Verify Duplicate email validation");
    }

    //...
}
