package ir.ac.pgu.bountypalette.controllers;

import ir.ac.pgu.bountypalette.controllers.commands.CommentCommand;
import ir.ac.pgu.bountypalette.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Value
@RequiredArgsConstructor
@RequestMapping("/comments/{paletteId}")
public class CommentController {

    CommentService commentService;

    @GetMapping()
    public List<CommentCommand> findAllPaletteComments(@PathVariable String paletteId) {
        return commentService.findAllPaletteComments(UUID.fromString(paletteId));
    }

    @PostMapping()
    public void postComment(@PathVariable String paletteId,
                            @RequestParam String author,
                            @RequestParam String title,
                            @RequestParam String message) {
        commentService.createComment(author,title,message,UUID.fromString(paletteId));
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable String commentId){
        commentService.removeComment(UUID.fromString(commentId));
    }
}