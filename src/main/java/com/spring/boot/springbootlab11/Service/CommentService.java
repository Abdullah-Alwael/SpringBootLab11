package com.spring.boot.springbootlab11.Service;

import com.spring.boot.springbootlab11.Api.ApiException;
import com.spring.boot.springbootlab11.Model.Comment;
import com.spring.boot.springbootlab11.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> getAlComments(){
        return commentRepository.findAll();
    }

    public void updateComment(Integer comment_id, Comment comment){
        Comment oldComment = commentRepository.findCommentByCommentId(comment_id);

        if (oldComment == null){
            throw new ApiException("Error comment not found");
        }

        oldComment.setContent(comment.getContent());
        oldComment.setCommentDate(comment.getCommentDate());
        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());

        commentRepository.save(oldComment);

    }

    public void deleteComment(Integer comment_id){
        Comment oldComment = commentRepository.findCommentByCommentId(comment_id);

        if (oldComment == null){
            throw new ApiException("Error comment not found");
        }

        commentRepository.delete(oldComment);

    }

    // Extra:
    // filter comment by content and postId
    public List<Comment> filterCommentsByContentAndPostId(String content, Integer postId){
        return commentRepository.filterCommentsByContentAndPostId(content,postId);
    }

    // filter comment between two dates and postId
    public List<Comment> findCommentsByCommentDateBetweenAndPostId(LocalDate commentDateAfter,
                                                                   LocalDate commentDateBefore,
                                                                   Integer postId){

        return commentRepository.findCommentsByCommentDateBetweenAndPostId(commentDateAfter, commentDateBefore, postId);

    }

    // TODO get all comments that are under a category

}
