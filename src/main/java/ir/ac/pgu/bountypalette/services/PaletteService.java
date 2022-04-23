package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.PaletteCommand;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PaletteService {

    List<PaletteCommand> findAllPalettes();

    PaletteCommand findPaletteById(UUID id);

    PaletteCommand createPalette(String color1, String color2, String color3, String color4, UUID categoryId);

    PaletteCommand createPaletteByCategoryName(String color1, String color2, String color3, String color4, String CategoryName);

    PaletteCommand addToCategory(UUID paletteId, UUID categoryId);

    PaletteCommand likePalette(UUID paletteId);

    void removePalette(UUID id);

    PaletteCommand updatePalette(String paletteId, String color1, String color2, String color3, String color4);

    PaletteCommand dislikePalette(UUID paletteId);

    List<PaletteCommand> findAllByCategoryName(String categoryName, Pageable pageable);
}
