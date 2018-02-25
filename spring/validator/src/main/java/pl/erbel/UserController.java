package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


/**
 * 1. Dodanie walidacji
 * 2. Obsługa błędów
 * 3. Wyświetlanie błędów w formularzu
 * 4. Przełączenie na JPA i bazę danych
 */

@Controller
public class UserController extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER =
            Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.
                    getAllErrors().
                    forEach(LOGGER::warn);
            return "form";
        }
        LOGGER.info(user);
        userService.createUser(user);
        LOGGER.info("saved");
        return "success";
    }

    //    @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String addUserForm(User user) {
        return "form";
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
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "list";
    }

}
