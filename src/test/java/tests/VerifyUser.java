package tests;

import com.api.models.Users.User;
import com.api.services.Users.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyUser {

    @Test(priority = 1)
    public void testGetUser() {
        UserService userService = new UserService();
        String username = "Delphine";
        User user = userService.getUser(username);
        Assert.assertNotNull(user);
        Assert.assertEquals(username, user.getUsername());
   }
}
