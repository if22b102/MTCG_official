package at.fhtw.sampleapp.service;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.controller.UserController;

public class UserService implements Service {
    private final UserController userController;

    public UserService() {
        this.userController = new UserController();    }


    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.GET) {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, "endpoint not done");
        } else if (request.getMethod() == Method.POST) {
            return this.userController.handlePostRequest(request);
        } else if (request.getMethod() == Method.PUT) {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, "endpoint not done");
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }


}
