package alibek.shop.controller;

import alibek.shop.entity.Product;
import alibek.shop.entity.Review;
import alibek.shop.entity.Users;
import alibek.shop.service.ProductService;
import alibek.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "admin/reviews")
@Controller
public class AdminReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String getReviewsByStatusFalse(Model model) {
        List<Review> reviews = reviewService.getReviewByStatusFalse();
        model.addAttribute("reviews", reviews);
        return "adminProduct/admin_reviews_page";
    }

    @PostMapping
    public String setStatusTrueToReview(@RequestParam(name = "userId") Long userId,
                                        @RequestParam(name = "productId") Long productId) {
        reviewService.publicReview(userId, productId);
        return "redirect:/admin/reviews";
    }
}
