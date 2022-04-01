package ir.ac.pgu.bountypalette.bootstrap;

import ir.ac.pgu.bountypalette.domain.Category;
import ir.ac.pgu.bountypalette.domain.Comment;
import ir.ac.pgu.bountypalette.domain.Palette;
import ir.ac.pgu.bountypalette.repositories.CategoryRepository;
import ir.ac.pgu.bountypalette.repositories.CommentRepository;
import ir.ac.pgu.bountypalette.repositories.PaletteRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Value
@Component
public class BootstrapData implements CommandLineRunner {

    CategoryRepository categoryRepository;
    PaletteRepository paletteRepository;
    CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        var category1 = new Category("summer");
        var category2 = new Category("winter");
        var category3 = new Category("spring");
        var category4 = new Category("autumn");

        categoryRepository.saveAll(List.of(category1, category2, category3, category4));

        for (int i = 0; i < 20; i++) {
            var palette = new Palette("123456", "123456", "123456", "123456");
            palette.setCreationDate(Instant.now());

            paletteRepository.save(palette);

            category1.addPalette(palette);
            categoryRepository.save(category1);
            paletteRepository.save(palette);
        }


        for (int i = 0; i < 20; i++) {
            var palette = new Palette("123456", "123456", "123456", "123456");
            palette.setCreationDate(Instant.now());

            paletteRepository.save(palette);

            category2.addPalette(palette);
            categoryRepository.save(category1);
            paletteRepository.save(palette);
        }

        for (int i = 0; i < 20; i++) {
            var palette = new Palette("123456", "123456", "123456", "123456");
            palette.setCreationDate(Instant.now());

            paletteRepository.save(palette);

            category3.addPalette(palette);
            categoryRepository.save(category1);
            paletteRepository.save(palette);
        }

        for (int i = 0; i < 20; i++) {
            var palette = new Palette("123456", "123456", "123456", "123456");
            palette.setCreationDate(Instant.now());

            paletteRepository.save(palette);

            category4.addPalette(palette);
            categoryRepository.save(category1);
            paletteRepository.save(palette);
        }

        var palettes = paletteRepository.findAll();

        for (Palette palette : palettes) {
            for (int i = 0; i < 10; i++) {

                var comment = new Comment("author","title","message");
                commentRepository.save(comment);

                palette.addComment(comment);
                paletteRepository.save(palette);
            }
        }

    }
}
