package ir.ac.pgu.bountypalette.services;

import ir.ac.pgu.bountypalette.controllers.commands.CommentCommand;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentCommand> findAllPaletteComments(UUID paletteId);

    void createComment(String author, String title, String message,UUID paletteId);

    void removeComment(UUID commentId);
}
