package ir.ac.pgu.bountypalette.controllers;

import ir.ac.pgu.bountypalette.controllers.commands.PaletteCommand;
import ir.ac.pgu.bountypalette.services.PaletteService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Value
@RequiredArgsConstructor
@RequestMapping("/palettes")
public class PaletteController {

    PaletteService paletteService;

    @GetMapping("/all")
    public List<PaletteCommand> getAllPalettes() {
        return paletteService.findAllPalettes();
    }

    @GetMapping("/{paletteId}")
    public PaletteCommand getPaletteById(@PathVariable String paletteId) {
        return paletteService.findPaletteById(UUID.fromString(paletteId));
    }

    @PutMapping("/{paletteId}")
    public PaletteCommand likePalette(@PathVariable String paletteId) {
        return paletteService.likePalette(UUID.fromString(paletteId));
    }

    @PostMapping("/{categoryId}")
    public PaletteCommand savePalette(@RequestParam String color1,
                                      @RequestParam String color2,
                                      @RequestParam String color3,
                                      @RequestParam String color4,
                                      @PathVariable String categoryId) {

        return paletteService.createPalette(color1, color2, color3, color4, UUID.fromString(categoryId));
    }

    @PutMapping("/category/{categoryId}/palette/{paletteId}")
    public PaletteCommand changeCategory(@PathVariable String categoryId, @PathVariable String paletteId) {
        return paletteService.addToCategory(UUID.fromString(paletteId), UUID.fromString(categoryId));
    }

    @DeleteMapping("/{paletteId}")
    public void deletePalette(@PathVariable String paletteId) {
        paletteService.removePalette(UUID.fromString(paletteId));
    }

    @PutMapping("/update/{paletteId}")
    public PaletteCommand updatePalette(@PathVariable String paletteId,
                                        @RequestParam String color1,
                                        @RequestParam String color2,
                                        @RequestParam String color3,
                                        @RequestParam String color4) {
        return paletteService.updatePalette(paletteId,color1,color2,color3,color4);
    }
}
