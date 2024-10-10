package at.fhtw.sampleapp.service;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.controller.SessionsController;
import at.fhtw.sampleapp.controller.UserController;


public class SessionsService implements Service {
    private final SessionsController sessionsController;

    public SessionsService() {
        this.sessionsController = new SessionsController();    }


    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.GET) {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, "endpoint not done");
        } else if (request.getMethod() == Method.POST) {
            return this.sessionsController.handlePostRequest(request);
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
