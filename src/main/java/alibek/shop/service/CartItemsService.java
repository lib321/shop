package alibek.shop.service;

import alibek.shop.entity.CartItems;
import alibek.shop.entity.Product;
import alibek.shop.entity.Users;
import alibek.shop.repository.CartItemsRepository;
import alibek.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    UserService userService;

    public void addProductToCart(Long productId, Integer count, Users user) {
        Product product = productRepository.findProductById(productId);
        CartItems cartItems = cartItemsRepository.findCartItemsByUsersIdAndProductId(user.getId(), productId);
        if (cartItems == null) {
            CartItems newCartItem = new CartItems();
            newCartItem.setProduct(product);
            newCartItem.setUsers(user);
            newCartItem.setCount(count);
            cartItemsRepository.save(newCartItem);
        }
    }

    public List<CartItems> getCartItem(Users user) {
        return cartItemsRepository.findCartItemsByUsersId(user.getId());
    }

    public void increaseProduct(Long productId) {
        Users user = userService.getCurrentUser();
        CartItems cartItems = cartItemsRepository.findCartItemsByUsersIdAndProductId(user.getId(), productId);
        cartItems.setCount(cartItems.getCount() + 1);
        cartItemsRepository.save(cartItems);
    }

    public void decreaseProduct(Long productId) {
        Users user = userService.getCurrentUser();
        CartItems cartItems = cartItemsRepository.findCartItemsByUsersIdAndProductId(user.getId(), productId);
        if (cartItems.getCount() != 1) {
            cartItems.setCount(cartItems.getCount() - 1);
            cartItemsRepository.save(cartItems);
        } else {
            cartItemsRepository.delete(cartItems);
        }
    }

    public void deleteProductFromCart(Long productId) {
        Users user = userService.getCurrentUser();
        CartItems cartItems = cartItemsRepository.findCartItemsByUsersIdAndProductId(user.getId(), productId);
        cartItemsRepository.delete(cartItems);
    }

    public int getPrice() {
        int sum = 0;
        Users user = userService.getCurrentUser();
        List<CartItems> cartItems = cartItemsRepository.findCartItemsByUsersId(user.getId());
        for (CartItems item : cartItems) {
            sum += item.getProduct().getPrice() * item.getCount();
        }
        return sum;
    }
}
