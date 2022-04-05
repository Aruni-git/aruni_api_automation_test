package tests;

import com.api.models.Posts.Post;
import com.api.models.Users.User;
import com.api.services.Comments.CommentService;
import com.api.services.Posts.PostService;
import com.api.services.Users.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyComments {
    @Test(priority = 1)
    public void getAllComments() {
        UserService userService = new UserService();
        String username = "Delphine";
        User user = userService.getUser(username);
        Assert.assertNotNull(user);
        Assert.assertEquals(username, user.getUsername());

        int userid = user.getId();
        PostService postService = new PostService();
        List<Post> posts = postService.getPostsByUser(userid);
        Assert.assertNotNull(posts);

        CommentService commentService = new CommentService();
        for (Post post : posts) {
            commentService.getAllComments(post);
        }
    }
}
