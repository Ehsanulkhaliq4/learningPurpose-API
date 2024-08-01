package com.exam.examserver.service.blogservice;

import com.exam.examserver.helper.PostDto;
import com.exam.examserver.model.blog.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto savePost(PostDto postDto) throws IOException;

    List<Post> getAllPosts();

    Post getPostById(Long postId);

    List<Post> searchByName(String name);

    void deletePost(Long id);
}
