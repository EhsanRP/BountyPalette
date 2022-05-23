package ir.ac.pgu.bountypalette.controllers.commands;

import ir.ac.pgu.bountypalette.domain.Palette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaletteCommand implements Serializable {

    private UUID id;

    private String color1;
    private String color2;
    private String color3;
    private String color4;

    private Integer likes;
    private Boolean isApproved;
    private Instant creationDate;

    private Set<CommentCommand> comments = new HashSet<>();

    private UUID categoryId;

    public PaletteCommand(Palette palette) {
        this.id = palette.getId();
        this.color1 = palette.getColor1();
        this.color2 = palette.getColor2();
        this.color3 = palette.getColor3();
        this.color4 = palette.getColor4();
        this.likes = palette.getLikes();
        this.creationDate = palette.getCreationDate();
        this.categoryId = palette.getCategory().getId();
        this.isApproved = palette.getIsApproved();

        this.comments = palette.getComments().stream().map(CommentCommand::new).collect(Collectors.toSet());
    }

    public static PaletteCommand createCommand(Palette palette) {
        return new PaletteCommand(palette);
    }

    public static List<PaletteCommand> createListCommand(List<Palette> palettes) {
        return palettes.stream().map(PaletteCommand::new).collect(Collectors.toList());
    }
}
