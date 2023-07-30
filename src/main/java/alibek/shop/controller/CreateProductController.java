package alibek.shop.controller;

import alibek.shop.entity.Category;
import alibek.shop.entity.Product;
import alibek.shop.repository.CategoryRepository;
import alibek.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/create_product")
public class CreateProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String createProductPage(Model model) {
        // HTML форма создания товара
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "create_product_page";
    }

    @PostMapping
    public String createProductAction(@RequestParam(name = "productName") String productName,
                                      @RequestParam(name = "productPrice") Long productPrice,
                                      @RequestParam(name = "categoryId") Category categoryId) {
        // Логика создания товара
        Product product = new Product();
        product.setCategory(categoryId);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);
        return "redirect:/create_product";
    }
}
