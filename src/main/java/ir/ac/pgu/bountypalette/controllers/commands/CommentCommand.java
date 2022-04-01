package ir.ac.pgu.bountypalette.controllers.commands;

import ir.ac.pgu.bountypalette.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCommand {

    private UUID id;

    private String author;
    private String title;
    private String message;

    private UUID paletteId;

    public CommentCommand(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.title = comment.getTitle();
        this.message = comment.getMessage();
        this.paletteId = comment.getPalette().getId();
    }

    public static CommentCommand createCommand(Comment comment) {
        return new CommentCommand(comment);
    }

    public static List<CommentCommand> createListCommand(List<Comment> comments) {
        return comments.stream().map(CommentCommand::new).collect(Collectors.toList());
    }
}
