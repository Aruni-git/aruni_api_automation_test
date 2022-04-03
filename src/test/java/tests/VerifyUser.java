package tests;

import com.api.models.Users.User;
import com.api.services.Users.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

public class VerifyUser {

    @Test(priority = 1)
    public void testGetUser() {
        UserService userService = new UserService();
        String userName = "Delphine";
        List<User> users = userService.getUser(userName);
        Assert.assertNotNull(users);
        User requestedUser = users.stream()
                .filter(user -> userName.equals(user.getUsername()))
                .findAny()
                .orElse(null);
        Assert.assertNotNull(requestedUser);

    }
}
