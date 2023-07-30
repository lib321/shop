package alibek.shop.repository;

import alibek.shop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewsByStatusTrueAndProductId(Long productId);

    Review findReviewByUsersIdAndProductId(Long userId, Long productId);

    List<Review> findReviewsByStatusFalse();

    List<Review> findReviewsByProductId(Long productId);

    Review findReviewByStatusFalseAndUsersIdAndProductId(Long userId, Long productId);

    Review findReviewByStatusTrueAndUsersIdAndProductId(Long userId, Long productId);
}
