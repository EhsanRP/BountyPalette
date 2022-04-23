package ir.ac.pgu.bountypalette.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String author;
    private String title;

    @Lob
    private String message;

    @ManyToOne
    private Palette palette;

    public Comment(String author, String title, String message) {
        this.author = author;
        this.title = title;
        this.message = message;
    }
}
