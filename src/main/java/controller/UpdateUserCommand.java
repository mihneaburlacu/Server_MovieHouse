package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.Enums.Role;
import model.User;

public class UpdateUserCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        String[] split = data.split(",");
        try {
            dbContextUser.updateUser(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4]);

//            User sentUser = new User();
//            String role = split[4];
//            if (role.equals("ADMINISTRATOR")) {
//                sentUser = new User(Integer.parseInt(split[0]), split[1], split[2], split[3], Role.ADMINISTRATOR);
//            } else if (role.equals("MANAGER")) {
//                sentUser = new User(Integer.parseInt(split[0]), split[1], split[2], split[3], Role.MANAGER);
//            } else {
//                sentUser = new User(Integer.parseInt(split[0]), split[1], split[2], split[3], Role.EMPLOYEE);
//            }
//
//
//            String dataJSON = objectMapper.writeValueAsString(sentUser);
//            ICommand command = new SendEmailCommand();
//            String email = command.execute(dataJSON);
//
//            if(email.equals("")){
//                return "no email";
//            }

            return "updated";
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }
}
