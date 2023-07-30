package alibek.shop.controller;

import alibek.shop.entity.Users;
import alibek.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security_controller")
public class SecurityController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/first_resource")
    public String firstResource() {
        return "SecurityController.firstResource()";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource() {
        return "SecurityController.secondResource()";
    }

    @GetMapping("/current_user")
    public String currentUser() {
        Users user = userService.getCurrentUser();
        return user.getLogin();
    }
}
