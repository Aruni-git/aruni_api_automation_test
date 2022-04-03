package com.api.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import com.api.models.User;
import io.restassured.specification.RequestSpecification;
import utils.RestClient;

import java.lang.reflect.Type;
import java.util.*;

public class UserService {


    public User getAllPosts() {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "posts");
        Response response = restClient.get(null, null, null);
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(response.getBody().asString(), User.class);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;

    }


    public User getAllUsers() {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users");
        Response response = restClient.get(null, null, null);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Type type = new TypeReference<ArrayList<User>>() {
            }.getType();
            return mapper.readValue(response.getBody().as(type).toString(), User.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }

    public List<User> getUser(String username) {
        //  RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users?username=Delphine");
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users");
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("username", username);
        }};
        Response response;
        response = restClient.get(null, null, map);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            String json = response.getBody().asString();
            users= new ObjectMapper().readValue(json, new TypeReference<ArrayList <User>>() {
            });

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return users;
    }
    public void getUser1() {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "users");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

    }

/*    public List<User> getAllUser() {
        Response response;
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .get()
                .andReturn();
        Type type = new TypeReference<List<User>>() {
        }.getType();
        List<User> userList = response.as(type);
        return userList;
    }*/

}


