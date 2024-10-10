package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.Repos.UserRepository;
import at.fhtw.sampleapp.dal.UnitOfWork;
import at.fhtw.sampleapp.model.UserCredentials;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserController extends Controller {

    private UserRepository userRepository;
    public UserController() {
        userRepository = new UserRepository(new UnitOfWork());
    }



    public Response handlePostRequest(Request request) {
        try {

            String bodyString = request.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode requestBody = mapper.readTree(bodyString);
            // usernam und pw vom req body holen
            String username = requestBody.get("Username").asText();
            String password = requestBody.get("Password").asText();

            //daten in usercredentials ablegen
            UserCredentials userCredentials = new UserCredentials(username, password);
            if(userRepository.regUser(username, userCredentials.getPassword())){
                return new Response(HttpStatus.CREATED, ContentType.PLAIN_TEXT, "Erfolgreich registriert " + username);

            }else {
                return new Response(HttpStatus.FORBIDDEN, ContentType.PLAIN_TEXT, "User: " + username + " already exists");
            }


        } catch (Exception e) {
            // Wenn Invalid Req Body
            e.printStackTrace();
            String response = "invalid JSON data provided";
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, response);

        }
    }


}

