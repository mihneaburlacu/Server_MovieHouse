package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.User;

public class DeleteUserByIDCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        try {
            String query = dbContextUser.createDeleteQueryByID(Integer.parseInt(data));
            dbContextUser.deleteUser(query);

            return "deleted";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
