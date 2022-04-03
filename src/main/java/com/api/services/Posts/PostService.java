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

import static java.util.stream.Collectors.toList;

public class PostService {
    public  List<Post> getPostsByUser(int userId) {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "posts");
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("userid", userId);
        }};

        Response response = restClient.get(null, null, map);
        List<Post> posts = null;
        try {

            String json = response.getBody().asString();
            posts = new ObjectMapper().readValue(json, new TypeReference<ArrayList<Post>>() {

            });

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return posts;
    }
}
