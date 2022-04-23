package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Palette;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PaletteRepository extends PagingAndSortingRepository<Palette, UUID> {

    List<Palette> findAllByCategory_Name(String name, Pageable pageable);

}
