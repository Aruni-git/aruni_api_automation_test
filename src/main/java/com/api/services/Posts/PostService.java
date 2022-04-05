package com.api.services.Posts;

import com.api.models.Posts.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import utils.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService {
    public  List<Post> getPostsByUser(int userId) {
        //Calling RestClient method
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "posts");

        //Initializing hash map and sending parameter value
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("userid", userId);
        }};
        //Get the response for the GET method
        Response response = restClient.get(null, null, map);
        System.out.println("Response status code = " + response.getStatusCode() + ", Response status line = " + response.getStatusLine());

        //Initializing posts list
        List<Post> posts = null;

        try {
            /*Converting json to a string*/
            String json = response.getBody().asString();
            posts = new ObjectMapper().readValue(json, new TypeReference<ArrayList<Post>>() {
            });

            System.out.println("Response Body is =>  " + response.asString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return posts;
    }
}
