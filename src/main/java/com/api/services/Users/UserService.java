package com.api.services.Users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import com.api.models.Users.User;


import utils.RestClient;
import java.util.*;

public class UserService {

    public User getUser(String username) {
        /*Calling RestClient method*/
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users");

        /*Initializing hash map and sending parameter value*/
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("username", username);
        }};
        /*Get the response for the GET method*/
        Response response;
        response = restClient.get(null, null, map);
        System.out.println("Response status code = " + response.getStatusCode() + ", Response status line = " + response.getStatusLine());

        /*Initializing  requestedUser*/
        User requestedUser = null;

        try {
            /*Converting json to a string*/
            String json = response.getBody().asString();
            List<User> users = new ObjectMapper().readValue(json, new TypeReference<ArrayList<User>>() {
            });

            requestedUser = users.stream()
                    .filter(user -> username.equals(user.getUsername()))
                    .findAny()
                    .orElse(null);

            System.out.println("Response Body is =>  " + response.asString());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return requestedUser;
    }
}


