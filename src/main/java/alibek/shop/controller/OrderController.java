package alibek.shop.controller;

import alibek.shop.entity.Orders;
import alibek.shop.entity.Users;
import alibek.shop.service.OrderService;
import alibek.shop.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/create_order")
    public String createOrder() {
        return "/create_order_page";
    }

    @PostMapping(path = "/create_order")
    public String createOrderAction(@RequestParam(name = "adress") String adress) {
        if (!adress.isEmpty()) {
            orderService.createOrder(adress);
            return "redirect:/products";
        } else {
            return "redirect:/order/create_order";
        }
    }
}
