package tests;

import com.api.models.User;
import com.api.services.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

public class VerifyUser {

/*    @Test(priority = 1)
    public void testGetAllUser() {
        UserService userService = new UserService();
        userService.getAllUsers();
    }*/
    @Test(priority = 2)
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
