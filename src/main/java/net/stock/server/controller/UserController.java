package net.stock.server.controller;

import net.stock.server.model.Category;
import net.stock.server.model.User;
import net.stock.server.service.CategoryService;
import net.stock.server.service.SecurityService;
import net.stock.server.service.UserService;
import net.stock.server.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public String table(){
        return "table";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userprof(Model model, Principal principal){
        if(principal != null){
            model.addAttribute("userprof",getUser(principal.getName()));
        }
            model.addAttribute("categoryForm",new Category());
        return "user";
    }
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("categoryForm") Category categoryForm, BindingResult bindingResult, Model model) {
        Category existing = categoryService.getByUserId(categoryForm.getUserId());
        if(existing != null) {categoryForm.setId(existing.getId());
        }
        categoryService.save(categoryForm);
     return "redirect:/user";
    }


    private User getUser(String username){
        return userService.findByUsername(username);
    }

    private  Category getCategoty(long id){
        return categoryService.getByUserId(id);
    }
}
