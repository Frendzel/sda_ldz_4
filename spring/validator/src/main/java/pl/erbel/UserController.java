package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "update") //@RequestMapping(method = RequestMethod.PUT)
    public String updateUser(User user) {
        return "update";
    }

    @RequestMapping(path = "update", method = RequestMethod.PUT)
//    @PutMapping(path = "update")
    public String update(@Valid User user,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.
                    getAllErrors().
                    forEach(LOGGER::warn);
            return "update";
        }
        try {
            userService.updateUser(user);
            model.addAttribute("login", user.getLogin());
            return "success";
        } catch (UserNotFoundException e) {
            LOGGER.error("USER NOT FOUND: " + user.getLogin());
            return "userNotFound";
        }
    }

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


    @RequestMapping(path = "delete", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam String login, Model model) {
        try {
            model.addAttribute("login", login);
            userService.deleteUser(login);
            return "success";
        } catch (UserNotFoundException e) {
            LOGGER.info("USER NOT FOUND: " + login);
            return "userNotFound";
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.GET)
    public String deleteUser(User user) {
        return "deleteUser";
    }

    @RequestMapping("findAll")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "list";
    }

}
