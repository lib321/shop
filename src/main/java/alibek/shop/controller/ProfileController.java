package alibek.shop.controller;

import alibek.shop.entity.Orders;
import alibek.shop.entity.Users;
import alibek.shop.service.CartItemsService;
import alibek.shop.service.OrderService;
import alibek.shop.service.UserService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "/profile")
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getProfileOrder(Model model) {
        if (userService.getCurrentUser() != null) {
            Users user = userService.getCurrentUser();
            List<Orders> orders = orderService.getOrders(user);
            model.addAttribute("user", user);
            model.addAttribute("orders", orders);
        }
        return "/profile_page";
    }
}
