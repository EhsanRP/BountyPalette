package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
