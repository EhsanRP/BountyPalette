package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Palette;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PaletteRepository extends PagingAndSortingRepository<Palette, UUID> {

    List<Palette> findAllByCategory_NameIgnoreCaseAndIsApproved(String name,Boolean isApproved, Pageable pageable);

    List<Palette> findAllByIsApproved(Boolean isApproved);

    List<Palette> findAllBy();

    List<Palette> findAllByIdIn(List<UUID> idList);

}
