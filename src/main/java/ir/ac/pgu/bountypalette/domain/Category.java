package ir.ac.pgu.bountypalette.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Palette> palettes = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void addPalette(Palette palette){
        this.palettes.add(palette);
        palette.setCategory(this);
    }
}
