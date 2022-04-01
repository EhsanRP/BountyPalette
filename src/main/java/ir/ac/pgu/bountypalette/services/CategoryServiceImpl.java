package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.CategoryCommand;
import ir.ac.pgu.bountypalette.domain.Category;
import ir.ac.pgu.bountypalette.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Value
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Override
    public List<CategoryCommand> findAllCategories() {
        return CategoryCommand.createListCommand(categoryRepository.findAll());
    }

    @Override
    public CategoryCommand findCategoryById(UUID id) {
        return CategoryCommand.createCommand(categoryRepository.findById(id).get());
    }

    @Override
    public CategoryCommand createCategory(String name) {

        var oldCategory = categoryRepository.findByNameIgnoreCase(name);
        if (oldCategory.isPresent())
            return CategoryCommand.createCommand(categoryRepository.findByNameIgnoreCase(name).get());

        var category = new Category(name);
        var savedCategory = categoryRepository.save(category);
        return CategoryCommand.createCommand(category);
    }

    @Override
    public void removeCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryCommand updateCategory(UUID id, String name) {
        var oldCategory = categoryRepository.findById(id).get();
        oldCategory.setName(name);
        var newCategory = categoryRepository.save(oldCategory);
        return CategoryCommand.createCommand(newCategory);
    }
}
