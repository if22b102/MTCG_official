package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.Repos.SessionsRepository;
import at.fhtw.sampleapp.Repos.UserRepository;
import at.fhtw.sampleapp.dal.UnitOfWork;
import at.fhtw.sampleapp.model.UserCredentials;
import at.fhtw.sampleapp.model.UserStore;
import at.fhtw.sampleapp.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SessionsController extends Controller{

    private SessionsRepository sessionsRepository;
    public SessionsController() {
        sessionsRepository = new SessionsRepository(new UnitOfWork());
    }



    public Response handlePostRequest(Request request) {
        try {

            String bodyString = request.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode requestBody = mapper.readTree(bodyString);
            // usernam und pw vom req body holen
            String username = requestBody.get("Username").asText();
            String password = requestBody.get("Password").asText();



            if(sessionsRepository.userLogin(username, password)) {
                System.out.println("username ist: " + username);
                return switch (username) {
                    case "altenhof" -> new Response(HttpStatus.OK, ContentType.JSON, "{'Token': altenhof-mtcgToken}");
                    case "kienboec" -> new Response(HttpStatus.OK, ContentType.JSON, "{'Token': kienboec-mtcgToken}");
                    case "admin" -> new Response(HttpStatus.OK, ContentType.JSON, "{'Token': admin-mtcgToken}");
                    case null, default -> new Response(HttpStatus.NOT_FOUND, ContentType.PLAIN_TEXT, "User not found");
                };

            }else {
                return new Response(HttpStatus.FORBIDDEN, ContentType.PLAIN_TEXT, "Login Daten stimmen nicht");
            }

        } catch (Exception e) {
            // Wenn Invalid Req Body
            e.printStackTrace();
            String response = "invalid JSON data provided";
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, response);

        }
    }

}

