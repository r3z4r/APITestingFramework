package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Users;

import java.io.File;


public class JsonMapper {
    private static String path = System.getProperty("user.dir")+"/resource/testData.json";

    public static void JsonMapper(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Users users = objectMapper.readValue(new File(path), Users.class);
            System.out.println(users.getData().get(0).getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
