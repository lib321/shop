package alibek.shop.controller;

import alibek.shop.entity.Category;
import alibek.shop.entity.Characteristic;
import alibek.shop.repository.CategoryRepository;
import alibek.shop.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/create_category")
public class CreateCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @GetMapping
    public String createCategoryPage() {
        return "/create_category_page";
    }

    @PostMapping
    public String createCategoryAction(@RequestParam(name = "categoryName") String categoryName,
                                       @RequestParam(name = "charName") String charName) {
        Category category = new Category();
        category.setName(categoryName);
        String[] split = charName.split(",\\s");
        categoryRepository.save(category);
        for (String s : split) {
            Characteristic characteristic = new Characteristic();
            characteristic.setName(s);
            characteristic.setCategory(category);
            characteristicRepository.save(characteristic);
        }
        return "redirect:/create_category";
    }
}
