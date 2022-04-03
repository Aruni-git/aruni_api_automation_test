package tests;

import com.api.services.UserService;
import org.testng.annotations.Test;

public class VerifyPosts {
    @Test
    public void testGetAllUser() {
        UserService userService = new UserService();
        userService.getAllPosts();
    }
}
