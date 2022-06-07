package ir.ac.pgu.bountypalette.controllers;

import ir.ac.pgu.bountypalette.controllers.commands.CategoryCommand;
import ir.ac.pgu.bountypalette.controllers.commands.PaletteCommand;
import ir.ac.pgu.bountypalette.controllers.commands.PostColors;
import ir.ac.pgu.bountypalette.domain.Palette;
import ir.ac.pgu.bountypalette.services.PaletteService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/all/{categoryId}")
    public List<PaletteCommand> getAllPalettesByCategoryId(@PathVariable String categoryId) {
        return paletteService.findAllByCategoryId(UUID.fromString(categoryId));
    }

    @GetMapping("/list/all")
    public List<PaletteCommand> getAllPalettesByIDList(@RequestBody List<UUID> idList){
        return paletteService.findAllPalettesById(idList);
    }


    @GetMapping("/all/{categoryName}")
    public List<PaletteCommand> getAllPalettesByCategoryName(@PathVariable String categoryName , @RequestParam int pageSize, @RequestParam int pageNumber) {
       Pageable page = PageRequest.of(pageNumber-1,pageSize);

        return paletteService.findAllByCategoryName(categoryName,page);
    }

    @GetMapping("/{paletteId}")
    public PaletteCommand getPaletteById(@PathVariable String paletteId) {
        return paletteService.findPaletteById(UUID.fromString(paletteId));
    }

    @PutMapping("/like/{paletteId}")
    public PaletteCommand likePalette(@PathVariable String paletteId) {
        System.out.println("Handling like");
        var data = paletteService.likePalette(UUID.fromString(paletteId));
        System.out.println("Request handled");
        return data;
    }


    @PutMapping("/dislike/{paletteId}")
    public PaletteCommand dislikePalette(@PathVariable String paletteId) {
        return paletteService.dislikePalette(UUID.fromString(paletteId));
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
        return paletteService.updatePalette(paletteId, color1, color2, color3, color4);
    }


    @PostMapping("/create/{categoryName}")
    public PaletteCommand createPaletteByCategoryName(@PathVariable String categoryName, @RequestBody PostColors postColors) {
        return paletteService.createPaletteByCategoryName(postColors.getColor1(), postColors.getColor2(), postColors.getColor3(), postColors.getColor4(), categoryName);
    }

    @GetMapping("/popular")
    public List<PaletteCommand> getPopularPalettes(){
        return paletteService.getPopular();
    }

    @GetMapping("/random")
    public List<PaletteCommand> getRandom(){
        return paletteService.getRandom();
    }

}
