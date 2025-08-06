package com.spring.boot.springbootlab11.Controller;

import com.spring.boot.springbootlab11.Api.ApiResponse;
import com.spring.boot.springbootlab11.Model.Post;
import com.spring.boot.springbootlab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post added successfully"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAlPosts());
    }

    @PutMapping("/update/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer post_id, @Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        postService.updatePost(post_id, post);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post updated successfully"));
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer post_id) {
        postService.deletePost(post_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post deleted successfully"));
    }

    @GetMapping("/filter/user/{userId}/category/{categoryId}/date-between/{after}/{before}")
    public ResponseEntity<?> filterByUserIdAndCategoryIdWithPublishDateBetween(@PathVariable Integer userId,
                                                                               @PathVariable Integer categoryId,
                                                                               @PathVariable LocalDate after,
                                                                               @PathVariable LocalDate before) {

        return ResponseEntity.status(HttpStatus.OK).body(postService.filterByUserIdAndCategoryIdWithPublishDateBetween(
                userId,
                categoryId,
                after,
                before
        ));
    }

    @GetMapping("/filter/{query}/date-between/{after}/{before}")
    public ResponseEntity<?> filterPostsByTitleOrContentWithPublishDateBetween(@PathVariable String query,
                                                                               @PathVariable LocalDate after,
                                                                               @PathVariable LocalDate before){

        return ResponseEntity.status(HttpStatus.OK).body(postService.filterPostsByTitleOrContentWithPublishDateBetween(
                query,
                query,
                after,
                before));

    }
}
