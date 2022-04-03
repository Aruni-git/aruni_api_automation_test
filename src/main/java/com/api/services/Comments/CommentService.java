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
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com", "comments");
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("postId", post.getId());
        }};
        Response response = restClient.get(null, null, map);
        List<Comment> comments = null;
        try {
            String jason = response.getBody().asString();
            comments = new ObjectMapper().readValue(jason, new TypeReference<ArrayList<Comment>>() {

            });

            List emails = comments.stream().map(o -> o.getEmail()).collect(toList());
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            for (Object email : emails) {
                Matcher matcher = pattern.matcher((CharSequence) email);
                System.out.println(email + " : " + matcher.matches());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return comments;
    }

}
