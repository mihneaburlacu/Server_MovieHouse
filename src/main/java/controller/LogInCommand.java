package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.User;

public class LogInCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        String[] split = data.split(",");
        String username = split[0];
        String password = split[1];

        String query = dbContextUser.createFindQueryByUsernameAndPassword(username, password);
        User user = dbContextUser.findUser(query);

        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
