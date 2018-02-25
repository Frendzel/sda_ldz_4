package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {

    private static final Logger LOGGER =
            Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * Method will be used to create validated user
     */
    @RequestMapping(path = "add", method = RequestMethod.POST)
    public User addUser(@Valid @RequestBody User user) throws InterruptedException {
        return userService.createUser(user);
    }

    /**
     * Find user based on login
     */
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

    @RequestMapping("findByLogin")
    public User findUserByLoginThroughCrudRepository(@RequestParam String login) {
        return userService.findUserByLogin(login);
    }

}
