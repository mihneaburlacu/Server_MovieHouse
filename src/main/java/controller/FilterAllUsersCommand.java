package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.User;

import java.util.List;

public class FilterAllUsersCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        List<User> roleList = dbContextUser.findUsersByRole(data);
        try {
            return objectMapper.writeValueAsString(roleList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
