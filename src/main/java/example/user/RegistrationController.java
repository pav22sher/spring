package example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        User user=new User();
        model.addAttribute("user",user);
        return "registration";
    }

    @PostMapping ("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if(!userService.save(user)){
            model.addAttribute("hasUserError","Пользователь с таким именем уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }
}
