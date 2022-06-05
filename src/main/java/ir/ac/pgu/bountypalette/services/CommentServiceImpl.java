package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.CommentCommand;
import ir.ac.pgu.bountypalette.domain.Comment;
import ir.ac.pgu.bountypalette.repositories.CommentRepository;
import ir.ac.pgu.bountypalette.repositories.PaletteRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    PaletteRepository paletteRepository;
    CommentRepository commentRepository;

    @Override
    public List<CommentCommand> findAllPaletteComments(UUID paletteId) {

        var palette = paletteRepository.findById(paletteId).get();

        return CommentCommand.createListCommand(new ArrayList<>(palette.getComments()));
    }

    @Override
    public CommentCommand createComment(String author, String title, String message,UUID paletteId) {
        var comment = new Comment(author, title, message);
        commentRepository.save(comment);

        var palette = paletteRepository.findById(paletteId).get();
        palette.addComment(comment);

        paletteRepository.save(palette);
        var saved= commentRepository.save(comment);

        return CommentCommand.createCommand(saved);
    }

    @Override
    public void removeComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }
}
