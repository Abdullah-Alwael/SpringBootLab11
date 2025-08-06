package com.spring.boot.springbootlab11.Service;

import com.spring.boot.springbootlab11.Api.ApiException;
import com.spring.boot.springbootlab11.Model.Post;
import com.spring.boot.springbootlab11.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void addPost(Post post) {
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }

    public List<Post> getAlPosts() {
        return postRepository.findAll();
    }

    public void updatePost(Integer post_id, Post post) {
        Post oldPost = postRepository.findPostByPostId(post_id);

        if (oldPost == null) {
            throw new ApiException("Error post not found");
        }

        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setUserId(post.getUserId());

        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());

        postRepository.save(oldPost);

    }

    public void deletePost(Integer post_id) {
        Post oldPost = postRepository.findPostByPostId(post_id);

        if (oldPost == null) {
            throw new ApiException("Error post not found");
        }

        postRepository.delete(oldPost);

    }

    // Extra:
    // filter posts by user_id and category between two dates

    public List<Post> filterByUserIdAndCategoryIdWithPublishDateBetween(Integer userId, Integer categoryId,
                                                                        LocalDate after, LocalDate before) {

        return postRepository.filterByUserIdAndCategoryIdWithPublishDateBetween(userId, categoryId, after, before);

    }

    // search posts by title or content containing word between two dates
    public List<Post> filterPostsByTitleOrContentWithPublishDateBetween(String title, String content,
                                                                        LocalDate publishDateAfter,
                                                                        LocalDate publishDateBefore) {

        return postRepository.findPostsByTitleLikeOrContentLikeAndPublishDateBetween(title, content,
                publishDateAfter,
                publishDateBefore);
    }

    // get all postIds under certain category
    public List<Integer> getPostIdsUnderACategory(Integer categoryId){
        List<Post> postsUnderCategory = postRepository.findPostsByCategoryId(categoryId);
        List<Integer> postIdsUnderCategory = new ArrayList<>();

        for (Post p:postsUnderCategory){
            postIdsUnderCategory.add(p.getPostId());
        }

        return postIdsUnderCategory;

    }
}
