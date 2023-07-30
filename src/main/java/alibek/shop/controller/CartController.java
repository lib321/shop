package alibek.shop.controller;

import alibek.shop.entity.CartItems;
import alibek.shop.entity.Users;
import alibek.shop.service.CartItemsService;
import alibek.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/cartItem")
@Controller
public class CartController {

    @Autowired
    private CartItemsService cartItemsService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getCartItem(Model model) {
        if (userService.getCurrentUser() != null) {
            Users user = userService.getCurrentUser();
            List<CartItems> cartItems = cartItemsService.getCartItem(user);
            int sum = cartItemsService.getPrice();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("user", user);
            model.addAttribute("sum", sum);
        }
        return "/cart_item_page";
    }

    @GetMapping(path = "/increase_product")
    public String increaseProduct(@RequestParam(name = "productId") Long productId) {
        cartItemsService.increaseProduct(productId);
        return "redirect:/cartItem";
    }

    @GetMapping(path = "/decrease_product")
    public String decreaseProduct(@RequestParam(name = "productId") Long productId) {
        cartItemsService.decreaseProduct(productId);
        return "redirect:/cartItem";
    }

    @GetMapping(path = "/delete_cartItem")
    public String deleteCartItem(@RequestParam(name = "productId") Long productId) {
        cartItemsService.deleteProductFromCart(productId);
        return "redirect:/cartItem";
    }
}
