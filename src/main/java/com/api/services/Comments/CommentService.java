package com.api.services.Comments;

import com.api.models.Comments.Comment;
import com.api.models.Posts.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import utils.RestClient;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class CommentService {
    public List<Comment> getAllComments(Post post) {

        /*Calling RestClient method*/
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "comments");

        /*Initializing hash map and sending parameter value*/
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("postId", post.getId());
        }};
        /*Get the response for the GET method*/
        Response response = restClient.get(null, null, map);
        System.out.println("\n Comments for Post Id:" + post.getId() + ", Post Title:" + post.getTitle() + "\n Response status code = " + response.getStatusCode() + ", Response status line = " + response.getStatusLine());

        /*Initializing comment list*/
        List<Comment> comments = null;

        try {
            /*Converting json to a string*/
            String jason = response.getBody().asString();
            comments = new ObjectMapper().readValue(jason, new TypeReference<ArrayList<Comment>>() {
            });
            /* Assign email addresses in the response body to a list */
            List emails = comments.stream().map(o -> o.getEmail()).collect(toList());

            /*Define regular expression*/
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            /*Validate email addresses*/
            for (Object email : emails) {
                Matcher matcher = pattern.matcher((CharSequence) email);
                if (matcher.matches()) {
                    /*Print the emails that matching the regular expression*/
                    System.out.println("\n" + email + " : " + matcher.matches());

                } else {
                    System.out.println("Wrong email format = " + email);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return comments;
    }
}
