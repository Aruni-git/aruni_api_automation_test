package com.api.services.Users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import com.api.models.Users.User;
import utils.RestClient;
import java.util.*;

public class UserService {

    public User getUser(String username) {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users");
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("username", username);
        }};
        Response response;
        response = restClient.get(null, null, map);
        User requestedUser = null;
        try {
            String json = response.getBody().asString();
            List<User> users= new ObjectMapper().readValue(json, new TypeReference<ArrayList <User>>() {
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


