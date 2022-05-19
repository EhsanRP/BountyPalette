package ir.ac.pgu.bountypalette.controllers;


import ir.ac.pgu.bountypalette.controllers.commands.CategoryCommand;
import ir.ac.pgu.bountypalette.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    public static final String BASE_URL = "127.0.0.1:8080/category";

    CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryCommand> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryCommand findCategoryById(@PathVariable String categoryId) {
        return categoryService.findCategoryById(UUID.fromString(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.removeCategory(UUID.fromString(categoryId));
    }

    @PostMapping()
    public CategoryCommand createCategory(@RequestParam String name) {
        return categoryService.createCategory(name);
    }

    @PutMapping("/{categoryId}")
    public CategoryCommand updateCategory(@PathVariable String categoryId, @RequestParam String name) {
        return categoryService.updateCategory(UUID.fromString(categoryId), name);
    }

    @GetMapping("/size/{categoryName}")
    public int getCategorySize(@PathVariable String categoryName){
        return categoryService.getCategorySizeByName(categoryName);
    }

    @GetMapping("/category/{categoryName}")
    public int getCategoryByName(@PathVariable String categoryName){
        return categoryService.getCategorySizeByName(categoryName);
    }

    @GetMapping("/names")
    public List<String> getCategoryNames(){
        return categoryService.findAllNames();
    }
}
