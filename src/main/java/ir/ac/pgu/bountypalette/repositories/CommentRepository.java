package ir.ac.pgu.bountypalette.repositories;

import ir.ac.pgu.bountypalette.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
