package controller;

import server.Server;

public class ServerController {
    public ServerController() {
        Server server = Server.getInstance();
        server.start(6666);
    }
}
