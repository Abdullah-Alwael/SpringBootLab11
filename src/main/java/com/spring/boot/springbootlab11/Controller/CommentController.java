package com.spring.boot.springbootlab11.Controller;

import com.spring.boot.springbootlab11.Api.ApiResponse;
import com.spring.boot.springbootlab11.Model.Comment;
import com.spring.boot.springbootlab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment added successfully"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAlComments());
    }

    @PutMapping("/update/{comment_id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer comment_id, @Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        commentService.updateComment(comment_id, comment);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer comment_id) {
        commentService.deleteComment(comment_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment deleted successfully"));
    }

    @GetMapping("/filter/{content}/{postId}")
    public ResponseEntity<?> filterCommentsByContentAndPostId(@PathVariable String content, @PathVariable Integer postId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.filterCommentsByContentAndPostId(content,
                postId));

    }

    @GetMapping("/filter/date-between/{after}/{before}/{postId}")
    public ResponseEntity<?> findCommentsByCommentDateBetweenAndPostId(@PathVariable LocalDate after,
                                                                       @PathVariable LocalDate before,
                                                                       @PathVariable Integer postId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findCommentsByCommentDateBetweenAndPostId(
                after,
                before,
                postId));

    }
}
