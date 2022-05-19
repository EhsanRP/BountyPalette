package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.CategoryCommand;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryCommand> findAllCategories();

    CategoryCommand findCategoryById(UUID id);

    CategoryCommand createCategory(String name);

    void removeCategory(UUID id);

    CategoryCommand updateCategory(UUID id,String name);

    int getCategorySizeByName(String name);

    List<String> findAllNames();
}
