package com.spring.boot.springbootlab11.Repository;

import com.spring.boot.springbootlab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findPostByPostId(Integer postId);

    // filter posts by user_id and category between two dates
    @Query("select p from Post p where p.userId=?1 and p.categoryId=?2 and p.publishDate between ?3 and ?4")
    List<Post> filterByUserIdAndCategoryIdWithPublishDateBetween(Integer userId, Integer categoryId,
                                                                 LocalDate after, LocalDate before);

    List<Post> findPostsByTitleLikeOrContentLikeAndPublishDateBetween(String title, String content,
                                                                      LocalDate publishDateAfter,
                                                                      LocalDate publishDateBefore);
}
