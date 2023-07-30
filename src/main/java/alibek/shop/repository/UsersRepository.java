package alibek.shop.repository;

import alibek.shop.entity.Orders;
import alibek.shop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);
    Users findUserById(Long userId);
}
