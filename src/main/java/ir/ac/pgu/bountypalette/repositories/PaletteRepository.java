package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Palette;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaletteRepository extends PagingAndSortingRepository<Palette, UUID> {

    List<Palette> findAllByCategory_NameIgnoreCaseAndIsApproved(String name,Boolean isApproved, Pageable pageable);

    List<Palette> findAllByIsApproved(Boolean isApproved);

    @Query(nativeQuery = true,value = "Select * from Palette order by random()")
    List<Palette> findAllRandom();
    List<Palette> findAllByIdIn(List<UUID> idList);

    List<Palette> findAllByCategory_Id(UUID categoryId);


}
