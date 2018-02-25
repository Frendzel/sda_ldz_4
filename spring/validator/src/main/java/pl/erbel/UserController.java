package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER =
            Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public User addUser(@Valid @RequestBody User user) throws InterruptedException {
        return userService.createUser(user);
    }

    @RequestMapping("find")
    public User findUser(@RequestParam String login) {
        try {
            return userService.findUser(login);
        } catch (UserNotFoundException e) {
            LOGGER.error("USER NOT FOUND: " + login);
        }
        return new User();
    }

    @RequestMapping(path = "update", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        try {
            return userService.updateUser(user);
        } catch (UserNotFoundException e) {
            LOGGER.error("USER NOT FOUND: " + user.getLogin());
        }
        return new User();
    }

    @RequestMapping(path = "delete", method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestParam String login) {
        try {
            return userService.deleteUser(login);
        } catch (UserNotFoundException e) {
            LOGGER.info("USER NOT FOUND: " + login);
        }
        return false;
    }

    @RequestMapping("findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

}
