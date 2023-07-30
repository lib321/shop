package alibek.shop.service;

import alibek.shop.entity.Product;
import alibek.shop.entity.Review;
import alibek.shop.entity.Users;
import alibek.shop.repository.ProductRepository;
import alibek.shop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Review> getReviewsById(Long productId) {
        return reviewRepository.findReviewsByStatusTrueAndProductId(productId);
    }

    public int getAvgMark(Long productId) {
        int avgMark = 0;
        List<Review> reviews = reviewRepository.findReviewsByStatusTrueAndProductId(productId);
        if (!reviews.isEmpty()) {
            for (Review review : reviews) {
                avgMark += review.getMark();
            }
            avgMark = avgMark / reviews.size();
        }
        return avgMark;
    }

    public Review getReviewByUserIdAndProductId(Long userId, Long productId) {
        return reviewRepository.findReviewByUsersIdAndProductId(userId, productId);
    }

    public Review getReviewByStatusTrue(Long userId, Long productId) {
        return reviewRepository.findReviewByStatusTrueAndUsersIdAndProductId(userId, productId);
    }

    public void updateReview(Long userId, Long productId, String text, Integer mark) {
        Review review = reviewRepository.findReviewByStatusTrueAndUsersIdAndProductId(userId, productId);
        review.setText(text);
        review.setMark(mark);
        reviewRepository.save(review);
    }

    public void createReview(Long productId, String text, Integer mark, Users user) {
        Product product = productRepository.findProductById(productId);
        Review review = new Review();
        review.setText(text);
        review.setMark(mark);
        review.setStatus(false);
        review.setPublished_at(LocalDateTime.now());
        review.setProduct(product);
        review.setUsers(user);
        reviewRepository.save(review);
    }

    public List<Review> getReviewByStatusFalse() {
        return reviewRepository.findReviewsByStatusFalse();
    }

    public void publicReview(Long userId, Long productId) {
        Review review = reviewRepository.findReviewByStatusFalseAndUsersIdAndProductId(userId, productId);
        review.setStatus(true);
        reviewRepository.save(review);
    }
}
