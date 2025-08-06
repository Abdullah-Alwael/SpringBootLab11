package com.spring.boot.springbootlab11.Repository;

import com.spring.boot.springbootlab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findCommentByCommentId(Integer commentId);

    // filter comment by content and postId
    @Query("select c from Comment c where c.content like ?1 and c.postId = ?2")
    List<Comment> filterCommentsByContentAndPostId(String content, Integer postId);

    // filter comment between two dates and postId
    List<Comment> findCommentsByCommentDateBetweenAndPostId(LocalDate commentDateAfter,
                                                            LocalDate commentDateBefore,
                                                            Integer postId);
}
