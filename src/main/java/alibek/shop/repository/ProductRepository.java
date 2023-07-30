package alibek.shop.repository;

import alibek.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductByPriceBetween(int from, int to);

    Product findProductById(Long id);
}
