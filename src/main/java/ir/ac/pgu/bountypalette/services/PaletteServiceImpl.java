package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.PaletteCommand;
import ir.ac.pgu.bountypalette.domain.Palette;
import ir.ac.pgu.bountypalette.repositories.CategoryRepository;
import ir.ac.pgu.bountypalette.repositories.PaletteRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Value
@Service
public class PaletteServiceImpl implements PaletteService {

    CategoryRepository categoryRepository;
    PaletteRepository paletteRepository;

    @Override
    public List<PaletteCommand> findAllPalettes() {
        Pageable page = PageRequest.of(0, Integer.MAX_VALUE);
        return PaletteCommand.createListCommand(paletteRepository.findAll(page).stream().collect(Collectors.toList()));
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
    public PaletteCommand createPaletteByCategoryName(String color1, String color2, String color3, String color4, String CategoryName) {
        var category = categoryRepository.findByNameIgnoreCase(CategoryName).get();

        var palette = new Palette(color1, color2, color3, color4);
        palette.setCreationDate(Instant.now());
        var savedPalette = paletteRepository.save(palette);

        category.addPalette(savedPalette);

        categoryRepository.save(category);
        var result = paletteRepository.save(savedPalette);

        return PaletteCommand.createCommand(result);
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

    @Override
    public PaletteCommand dislikePalette(UUID paletteId) {
        var palette = paletteRepository.findById(paletteId).get();
        palette.decreaseLikes();

        var updatedPalette = paletteRepository.save(palette);
        return PaletteCommand.createCommand(updatedPalette);
    }

    @Override
    public List<PaletteCommand> findAllByCategoryName(String categoryName, Pageable pageable) {
        var category = categoryRepository.findByNameIgnoreCase(categoryName);
        var allPalettes = paletteRepository.findAllByCategory_NameIgnoreCaseAndIsApproved(categoryName, true, pageable);

        return PaletteCommand.createListCommand(allPalettes);
    }

    @Override
    public List<PaletteCommand> getPopular() {
        var sorted = paletteRepository.findAll(Sort.by("likes").descending());
        var list = new ArrayList<Palette>();
        sorted.forEach(list::add);
        return PaletteCommand.createListCommand(list);
    }

    @Override
    public List<PaletteCommand> findUnApprovedPalettes() {
        return PaletteCommand.createListCommand(paletteRepository.findAllByIsApproved(false));
    }

    @Override
    public List<PaletteCommand> findAllPalettesById(List<UUID> idList) {
        return PaletteCommand.createListCommand(paletteRepository.findAllByIdIn(idList));
    }
}
