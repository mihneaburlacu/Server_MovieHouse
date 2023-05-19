package server;

import controller.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private Server() {
        // Private constructor to prevent instantiation
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String inputLine = "";
        try {
            while ((inputLine = in.readLine()) != null) {
                String[] splitString = separateEndPoint(inputLine);
                String endpoint = splitString[0];
                String data = splitString[1];

                String send = handleEndpoint(endpoint, data);
                out.println(send);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String[] separateEndPoint(String endpoint) {
        String[] splitString = endpoint.split("/");

        return splitString;
    }

    public String handleEndpoint(String endpoint, String data) {
        if(endpoint.equals("login")) {
            ICommand command = new LogInCommand();
            return command.execute(data);
        }

        if(endpoint.equals("addUser")) {
            ICommand command = new AddUserCommand();
            return command.execute(data);
        }

        if(endpoint.equals("readUserByUsername")) {
            ICommand command = new ReadUserByUsernameCommand();
            return command.execute(data);
        }

        if(endpoint.equals("readUserByID")) {
            ICommand command = new ReadUserByIDCommand();
            return command.execute(data);
        }

        if(endpoint.equals("updateUser")) {
            ICommand command = new UpdateUserCommand();
            return command.execute(data);
        }

        if(endpoint.equals("deleteUserByID")){
            ICommand command = new DeleteUserByIDCommand();
            return command.execute(data);
        }

        if(endpoint.equals("deleteUserByUsername")){
            ICommand command = new DeleteUserByUsernameCommand();
            return command.execute(data);
        }

        if(endpoint.equals("viewAllUsers")) {
            ICommand command = new ViewAllUsersCommand();
            return command.execute(data);
        }

        if(endpoint.equals("filterAllUsers")) {
            ICommand command = new FilterAllUsersCommand();
            return command.execute(data);
        }

        if(endpoint.equals("viewAllMovies")) {
            ICommand command = new ViewAllMoviesCommand();
            return command.execute(data);
        }

        if(endpoint.equals("filterAllMovies")) {
            ICommand command = new FilterAllMoviesCommand();
            return command.execute(data);
        }

        if(endpoint.equals("addMovie")) {
            ICommand command = new AddMovieCommand();
            return command.execute(data);
        }

        if(endpoint.equals("readMovieByName")) {
            ICommand command = new ReadMovieByNameCommand();
            return command.execute(data);
        }

        if(endpoint.equals("readMovieByID")){
            ICommand command = new ReadMovieByIDCommand();
            return command.execute(data);
        }

        if(endpoint.equals("updateMovie")){
            ICommand command = new UpdateMovieCommand();
            return command.execute(data);
        }

        if(endpoint.equals("deleteMovieByName")) {
            ICommand command = new DeleteMovieByNameCommand();
            return command.execute(data);
        }

        if(endpoint.equals("deleteMovieByID")) {
            ICommand command = new DeleteMovieByIDCommand();
            return command.execute(data);
        }

        return "";
    }
}
