package alibek.shop.controller;

import alibek.shop.entity.Orders;
import alibek.shop.entity.enumeration.OrderStatus;
import alibek.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/admin/orders")
@Controller
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrders(Model model) {
        List<Orders> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "adminProduct/admin_orders_page";
    }

    @PostMapping
    public String accessOrder(@RequestParam(name = "orderId") Long orderId) {
        orderService.accessOrder(orderId);
        return "redirect:/admin/orders";
    }
}
