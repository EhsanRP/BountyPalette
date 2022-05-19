package ir.ac.pgu.bountypalette.controllers.commands;

import ir.ac.pgu.bountypalette.controllers.CategoryController;
import ir.ac.pgu.bountypalette.domain.Category;
import ir.ac.pgu.bountypalette.domain.Palette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand {

    private UUID id;

    private String name;

    private Set<UUID> palettes = new HashSet<>();


    public CategoryCommand(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.palettes = category.getPalettes().stream().map(Palette::getId).collect(Collectors.toSet());
    }

    public static CategoryCommand createCommand(Category category) {
        return new CategoryCommand(category);
    }

    public static List<CategoryCommand> createListCommand(List<Category> categories) {
        return categories.stream().map(CategoryCommand::new).collect(Collectors.toList());
    }


}
