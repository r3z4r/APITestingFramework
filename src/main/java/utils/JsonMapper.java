package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Users;

import java.io.File;


public class JsonMapper {
    private static String path = System.getProperty("user.dir")+"/resource/testData.json";

    public static Users getTestData(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Users users = objectMapper.readValue(new File(path), Users.class);
            return users;
        } catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
