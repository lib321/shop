package alibek.shop.service;


import alibek.shop.entity.*;
import alibek.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CharValueRepository charValueRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProducts(Long categoryId) {
        if (categoryId == null || categoryId == -1) {
            return productRepository.findAll();
        } else {
            return productRepository.findProductsByCategoryId(categoryId);
        }
    }

    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    public void deleteProduct(Long productId) {
        List<CharValue> charValues = charValueRepository.findCharValuesByProductId(productId);
        charValueRepository.deleteAll(charValues);
        List<Review> reviews = reviewRepository.findReviewsByProductId(productId);
        List<OrderProducts> orderProducts = orderProductsRepository.findOrderProductsByProductId(productId);
        List<CartItems> cartItems = cartItemsRepository.findCartItemsByProductId(productId);
        reviewRepository.deleteAll(reviews);
        orderProductsRepository.deleteAll(orderProducts);
        cartItemsRepository.deleteAll(cartItems);
        productRepository.deleteById(productId);
    }

    public void updateProductAction(Long productId, String productName, Long productPrice, List<String> values) {
        List<CharValue> charValues = charValueRepository.findCharValuesByProductId(productId);
        for (int i = 0; i < charValues.size(); i++) {
            charValues.get(i).setValue(values.get(i));
            charValueRepository.save(charValues.get(i));
        }
        Product product = productRepository.findProductById(productId);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);
    }

    public void createProduct(String productName, Long productPrice, Category categoryId,
                              List<Long> characteristicIds, List<String> values) {
        List<CharValue> charValues = new ArrayList<>();
        List<Characteristic> characteristics = characteristicRepository.findAllById(characteristicIds);
        Product product = new Product();
        product.setCategory(categoryId);
        product.setName(productName);
        product.setPrice(productPrice);
        product.setCharValues(charValues);
        productRepository.save(product);
        for (int i = 0; i < characteristics.size(); i++) {
            CharValue charValue = new CharValue();
            charValue.setValue(values.get(i));
            charValue.setProduct(product);
            charValue.setCharacteristic(characteristics.get(i));
            charValueRepository.save(charValue);
            charValues.add(charValue);
        }
    }
}
