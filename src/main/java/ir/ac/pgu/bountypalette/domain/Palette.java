package ir.ac.pgu.bountypalette.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "palette",columnNames = {"color1","color2","color3","color4"}),
})
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

    private Boolean isApproved = false;

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
        if(this.likes>0)
            this.likes--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palette palette = (Palette) o;
        return Objects.equals(color1, palette.color1) && Objects.equals(color2, palette.color2) && Objects.equals(color3, palette.color3) && Objects.equals(color4, palette.color4);
    }

}
