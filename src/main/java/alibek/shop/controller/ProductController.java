package alibek.shop.controller;

import alibek.shop.entity.CartItems;
import alibek.shop.entity.Product;
import alibek.shop.entity.Review;
import alibek.shop.entity.Users;
import alibek.shop.service.CartItemsService;
import alibek.shop.service.ProductService;
import alibek.shop.service.ReviewService;
import alibek.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "/product_page";
    }

    @GetMapping(path = "/details")
    public String getProductsAction(@RequestParam(name = "productId") Long productId,
                                    Model model) {
        Product product = productService.getProductById(productId);
        List<Review> reviews = reviewService.getReviewsById(productId);
        if (userService.getCurrentUser() != null) {
            Users user = userService.getCurrentUser();
            if (reviewService.getReviewByUserIdAndProductId(user.getId(), productId) != null) {
                Review review = reviewService.getReviewByStatusTrue(user.getId(), productId);
                model.addAttribute("review", review);
                model.addAttribute("user", user);
            }
        }
        int avgMark = reviewService.getAvgMark(productId);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgMark", avgMark);
        return "/product_details_page";
    }

    @PostMapping(path = "/create_review")
    public String createReview(@RequestParam(name = "productId") Long productId,
                               @RequestParam(name = "text") String text,
                               @RequestParam(name = "mark") Integer mark) {
        reviewService.createReview(productId, text, mark, userService.getCurrentUser());
        return "redirect:/products/details?productId=" + productId;
    }

    @PostMapping("/update_review")
    public String updateReview(@RequestParam(name = "productId") Long productId,
                               @RequestParam(name = "text") String text,
                               @RequestParam(name = "mark") Integer mark) {
        Users user = userService.getCurrentUser();
        reviewService.updateReview(user.getId(), productId, text, mark);
        return "redirect:/products";
    }

    @PostMapping(path = "/addProductToCart")
    public String createCartItem(@RequestParam(name = "productId") Long productId,
                                 @RequestParam(name = "count") Integer count) {
        if (userService.getCurrentUser() != null) {
            Users user = userService.getCurrentUser();
            cartItemsService.addProductToCart(productId, count, user);
        }
        return "redirect:/products";
    }
}

