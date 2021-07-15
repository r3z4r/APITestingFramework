package tests;

import helpers.UserHelper;
import model.Datum;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class getUsers {
    private UserHelper userHelper ;

    @BeforeClass
    public void init(){
        userHelper = new UserHelper();
    }

    @Test
    public void testGetAllUsers(){
        List<Datum> usersList = userHelper.getUsers();
        System.out.println(usersList.get(0).getName());
        Assert.assertNotNull(usersList,"users are coming");
    }
}
