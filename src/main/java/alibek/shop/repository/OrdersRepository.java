package alibek.shop.repository;

import alibek.shop.entity.Orders;
import alibek.shop.entity.Users;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findOrdersByUserId(Users userId);

    Orders findOrderById(Long orderId);
}
