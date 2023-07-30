package alibek.shop.repository;

import alibek.shop.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    List<Characteristic> findCharacteristicsByCategoryId(Long categoryId);
}
