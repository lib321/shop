package alibek.shop.repository;

import alibek.shop.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findCartItemsByUsersId(Long userId);

    CartItems findCartItemsByUsersIdAndProductId(Long userId, Long productId);

    List<CartItems> findCartItemsByProductId(Long productId);
}
