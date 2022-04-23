package ir.ac.pgu.bountypalette.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Palette implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String color1;
    private String color2;
    private String color3;
    private String color4;

    private Integer likes = 0;

    private Instant creationDate;

    @OneToMany(mappedBy = "palette", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private Category category;

    public Palette(String color1, String color2, String color3, String color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPalette(this);
    }

    public void increaseLikes() {
        this.likes++;
    }
    public void decreaseLikes() {
        this.likes--;
    }
}
