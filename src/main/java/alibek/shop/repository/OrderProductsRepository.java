package alibek.shop.repository;

import alibek.shop.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {

    List<OrderProducts> findOrderProductsByProductId(Long productId);
}
