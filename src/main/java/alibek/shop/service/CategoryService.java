package alibek.shop.service;

import alibek.shop.entity.Category;
import alibek.shop.entity.Characteristic;
import alibek.shop.repository.CategoryRepository;
import alibek.shop.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Characteristic> getCategoryById(Long categoryId) {
        return characteristicRepository.findCharacteristicsByCategoryId(categoryId);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public void setCharacteristicsToNewCategory(Characteristic characteristic) {
        characteristicRepository.save(characteristic);
    }
}
