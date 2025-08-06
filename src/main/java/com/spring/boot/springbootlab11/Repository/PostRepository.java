package com.spring.boot.springbootlab11.Repository;

import com.spring.boot.springbootlab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findPostByPostId(Integer postId);
}
