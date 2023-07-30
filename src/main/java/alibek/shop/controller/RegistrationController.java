package alibek.shop.controller;

import alibek.shop.entity.Users;
import alibek.shop.entity.enumeration.UserRole;
import alibek.shop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public String registrationUser() {
        return "/registration_page";
    }

    @PostMapping
    public String registrationUserAction(@RequestParam(name = "userLogin") String userLogin,
                                         @RequestParam(name = "userPassword") String userPassword,
                                         @RequestParam(name = "userName") String userName,
                                         @RequestParam(name = "userSurname") String userSurname) {
        Users user = new Users();
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        user.setName(userName);
        user.setSurname(userSurname);
        user.setRole(UserRole.USER);
        user.setRegistered_at(LocalDateTime.now());
        usersRepository.save(user);
        return "redirect:/registration";
    }
}
