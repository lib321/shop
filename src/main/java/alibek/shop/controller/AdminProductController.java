package alibek.shop.controller;

import alibek.shop.entity.Category;
import alibek.shop.entity.Characteristic;
import alibek.shop.entity.Product;
import alibek.shop.service.CategoryService;
import alibek.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/admin/products")
@Controller
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getProducts(Model model,
                              @RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getProducts(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "adminProduct/admin_products_page";
    }

    @PostMapping(path = "/delete")
    public String deleteProduct(@RequestParam(name = "productId") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin/products";
    }

    @GetMapping(path = "/update")
    public String updateProductPage(@RequestParam(name = "productId") Long productId,
                                    Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "adminProduct/admin_update_product_page";
    }

    @PostMapping(path = "/update")
    public String updateProductAction(@RequestParam(name = "productId") Long productId,
                                      @RequestParam(name = "productName") String productName,
                                      @RequestParam(name = "productPrice") Long productPrice,
                                      @RequestParam(name = "charValue") List<String> values) {
        productService.updateProductAction(productId, productName, productPrice, values);
        return "redirect:/admin/products";
    }

    @GetMapping(path = "/create")
    public String createProductPage(Model model,
                                    @RequestParam(name = "categoryId", required = false) Long categoryId) {
        if (categoryId == null) {
            List<Category> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            return "adminProduct/admin_choose_category_page";
        } else {
            List<Characteristic> characteristics = categoryService.getCategoryById(categoryId);
            model.addAttribute("characteristics", characteristics);
            model.addAttribute("categoryId", categoryId);
            return "adminProduct/admin_create_product_page";
        }
    }

    @PostMapping(path = "/create")
    public String createProductAction(@RequestParam(name = "productName") String productName,
                                      @RequestParam(name = "productPrice") Long productPrice,
                                      @RequestParam(name = "categoryId") Category categoryId,
                                      @RequestParam(name = "characteristic") List<Long> characteristicIds,
                                      @RequestParam(name = "value") List<String> values) {
        productService.createProduct(productName, productPrice, categoryId, characteristicIds, values);
        return "redirect:/admin/products";
    }
}
