package ir.ac.pgu.bountypalette.controllers.commands;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostColors {

    String color1;
    String color2;
    String color3;
    String color4;

    public PostColors(String color1, String color2, String color3, String color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public PostColors() {
    }
}
