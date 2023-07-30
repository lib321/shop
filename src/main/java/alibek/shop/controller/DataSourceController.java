package alibek.shop.controller;

import alibek.shop.entity.Product;
import alibek.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/data_controller")
public class DataSourceController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/first_resource")
    public String firstResource(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "data/first_resource_page";
    }

    // `http://localhost:8080/data_controller/second_resource?categoryId=2` - выводит на страницу только товары
    // категория которых равна 2.

    @GetMapping(path = "/second_resource")
    public String second_resource(@RequestParam(name = "categoryId", required = false) Long categoryId, Model model) {
        List<Product> products;
        if (categoryId == null) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findProductsByCategoryId(categoryId);
        }
        model.addAttribute("products", products);
        return "data/first_resource_page";
    }

    @GetMapping(path = "/third_resource")
    public String third_resource(
            @RequestParam(name = "from", required = false) Integer from,
            @RequestParam(name = "to", required = false) Integer to, Model model) {
        List<Product> products;
        if (from == null) {
            from = Integer.MIN_VALUE;
        }
        if (to == null) {
            to = Integer.MAX_VALUE;
        }
        if (from == Integer.MIN_VALUE && to == Integer.MAX_VALUE) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findProductByPriceBetween(from, to);
        }
        model.addAttribute("products", products);
        return "data/first_resource_page";
    }


}
