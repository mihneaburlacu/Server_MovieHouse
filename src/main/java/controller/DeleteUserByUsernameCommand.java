package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;

public class DeleteUserByUsernameCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        try {
            String query = dbContextUser.createDeleteQueryByUsername(data);
            dbContextUser.deleteUser(query);

            return "deleted";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
