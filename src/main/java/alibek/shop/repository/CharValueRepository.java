package alibek.shop.repository;

import alibek.shop.entity.CharValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharValueRepository extends JpaRepository<CharValue, Long> {

    List<CharValue> findCharValuesByProductId(Long productId);
}
