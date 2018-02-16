package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class CarMVCController extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER =
            Logger.getLogger(CarMVCController.class);

    @Autowired
    CarManager carManager;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/success").setViewName("success");
    }

    @PostMapping("/")
    public String submitForm(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error(bindingResult.getFieldErrors());
            return "form";
        }
        LOGGER.info(car);
        carManager.saveCar(car);
        return "redirect:/success"; // success
    }

    @GetMapping("/")
    public String showForm(Car car) {
        return "form";
    }

    //http://www.thymeleaf.org/doc/articles/springmvcaccessdata.html
    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String cars(Model model) {
        model.addAttribute("cars", carManager.findAll());
        return "list";
    }


}
