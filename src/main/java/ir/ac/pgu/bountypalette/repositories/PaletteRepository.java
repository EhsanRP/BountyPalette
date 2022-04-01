package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Palette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaletteRepository extends JpaRepository<Palette, UUID> {
}
