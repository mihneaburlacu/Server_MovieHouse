package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.User;

public class ReadUserByIDCommand implements  ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        User user = new User();
        try {
            String query = dbContextUser.createFindQueryByID(Integer.parseInt(data));
            user = dbContextUser.findUser(query);

            return objectMapper.writeValueAsString(user);
        } catch (JsonMappingException ex) {
            ex.printStackTrace();
            return "";
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
