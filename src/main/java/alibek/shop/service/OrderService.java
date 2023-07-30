package alibek.shop.service;

import alibek.shop.entity.CartItems;
import alibek.shop.entity.OrderProducts;
import alibek.shop.entity.Orders;
import alibek.shop.entity.Users;
import alibek.shop.entity.enumeration.OrderStatus;
import alibek.shop.repository.CartItemsRepository;
import alibek.shop.repository.OrderProductsRepository;
import alibek.shop.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private UserService userService;

    public void createOrder(String adress) {
        Users user = userService.getCurrentUser();
        List<CartItems> cartItems = cartItemsRepository.findCartItemsByUsersId(user.getId());
        if (!cartItems.isEmpty()) {
            List<OrderProducts> orderProducts = new ArrayList<>();
            Orders orders = new Orders();
            orders.setUserId(user);
            orders.setOrderProducts(orderProducts);
            orders.setCreated_at(LocalDateTime.now());
            orders.setOrderStatus(OrderStatus.CREATED);
            orders.setAdress(adress);
            ordersRepository.save(orders);
            for (CartItems item : cartItems) {
                OrderProducts orderProduct = new OrderProducts();
                orderProduct.setProduct(item.getProduct());
                orderProduct.setCount(item.getCount());
                orderProduct.setOrders(orders);
                orderProductsRepository.save(orderProduct);
                orderProducts.add(orderProduct);
            }
            cartItemsRepository.deleteAll(cartItems);
        }
    }

    public List<Orders> getOrders(Users userId) {
        return ordersRepository.findOrdersByUserId(userId);
    }

    public int getPrice(Long orderId) {
        Orders order = ordersRepository.findOrderById(orderId);
        int sum = 0;
        for (OrderProducts orderProduct : order.getOrderProducts()) {
            sum += orderProduct.getProduct().getPrice() * orderProduct.getCount();
        }
        return sum;
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public void accessOrder(Long orderId) {
        Orders order = ordersRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatus.PROCESSED);
        ordersRepository.save(order);
    }
}
