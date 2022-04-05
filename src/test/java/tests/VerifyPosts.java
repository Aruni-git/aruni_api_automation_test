package tests;

import com.api.models.Posts.Post;
import com.api.models.Users.User;
import com.api.services.Posts.PostService;
import com.api.services.Users.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyPosts {
    @Test
    public void testGetAllPosts() {
        UserService userService = new UserService();
        String username = "Delphine";
        User user = userService.getUser(username);

        int userid = user.getId();
        PostService postService = new PostService();
        List<Post> posts = postService.getPostsByUser(userid);
        Assert.assertNotNull(posts);
    }
}
