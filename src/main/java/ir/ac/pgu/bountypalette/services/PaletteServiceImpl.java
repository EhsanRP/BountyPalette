package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.PaletteCommand;
import ir.ac.pgu.bountypalette.domain.Palette;
import ir.ac.pgu.bountypalette.repositories.CategoryRepository;
import ir.ac.pgu.bountypalette.repositories.PaletteRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Value
@Service
public class PaletteServiceImpl implements PaletteService {

    CategoryRepository categoryRepository;
    PaletteRepository paletteRepository;

    @Override
    public List<PaletteCommand> findAllPalettes() {
        return PaletteCommand.createListCommand(paletteRepository.findAll());
    }

    @Override
    public PaletteCommand findPaletteById(UUID id) {
        return PaletteCommand.createCommand(paletteRepository.findById(id).get());
    }

    @Override
    public PaletteCommand createPalette(String color1, String color2, String color3, String color4, UUID categoryId) {

        var palette = new Palette(color1, color2, color3, color4);
        palette.setCreationDate(Instant.now());
        var savedPalette = paletteRepository.save(palette);

        return addToCategory(savedPalette.getId(), categoryId);
    }

    @Override
    public PaletteCommand addToCategory(UUID paletteId, UUID categoryId) {

        var category = categoryRepository.findById(categoryId).get();
        var palette = paletteRepository.findById(paletteId).get();

        category.addPalette(palette);

        categoryRepository.save(category);
        var paletteToReturn = paletteRepository.save(palette);

        return PaletteCommand.createCommand(paletteToReturn);
    }

    @Override
    public PaletteCommand likePalette(UUID paletteId) {
        var palette = paletteRepository.findById(paletteId).get();
        palette.increaseLikes();
        var savedPalette = paletteRepository.save(palette);
        return PaletteCommand.createCommand(savedPalette);
    }

    @Override
    public void removePalette(UUID id) {
        paletteRepository.deleteById(id);
    }

    @Override
    public PaletteCommand updatePalette(String paletteId, String color1, String color2, String color3, String color4) {

        var oldPalette = paletteRepository.findById(UUID.fromString(paletteId)).get();

        oldPalette.setColor1(color1);
        oldPalette.setColor2(color2);
        oldPalette.setColor3(color3);
        oldPalette.setColor4(color4);

        var newPalette = paletteRepository.save(oldPalette);

        return PaletteCommand.createCommand(newPalette);
    }
}
