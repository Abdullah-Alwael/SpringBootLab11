package com.spring.boot.springbootlab11.Service;

import com.spring.boot.springbootlab11.Api.ApiException;
import com.spring.boot.springbootlab11.Model.Post;
import com.spring.boot.springbootlab11.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void addPost(Post post){
        postRepository.save(post);
    }

    public List<Post> getAlPosts(){
        return postRepository.findAll();
    }

    public void updatePost(Integer post_id, Post post){
        Post oldPost = postRepository.findPostByPostId(post_id);

        if (oldPost == null){
            throw new ApiException("Error post not found");
        }

        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setUserId(post.getUserId());

        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setPublishDate(post.getPublishDate());

        postRepository.save(oldPost);

    }

    public void deletePost(Integer post_id){
        Post oldPost = postRepository.findPostByPostId(post_id);

        if (oldPost == null){
            throw new ApiException("Error post not found");
        }

        postRepository.delete(oldPost);

    }
}
