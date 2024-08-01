package com.exam.examserver.service.blogservice.impl;

import com.exam.examserver.helper.PostDto;
import com.exam.examserver.model.blog.Post;
import com.exam.examserver.repo.blogrepo.PostRepository;
import com.exam.examserver.service.blogservice.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    public PostDto savePost(PostDto postDto) throws IOException {
        Post post = new Post();
        post.setViewContent(0);
        post.setDate(new Date());
        post.setPostedBy(postDto.getPostedBy());
        post.setName(postDto.getName());
        post.setContent(postDto.getContent());
        post.setImg(postDto.getImg().getBytes());
        post.setTags(postDto.getTags());
        return repository.save(post).getDto();
    }

    public List<Post> getAllPosts(){
        return repository.findAll();
    }

    public Post getPostById(Long postId){
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setViewContent(post.getViewContent() + 1);
            return repository.save(post);
        }else {
            throw new EntityNotFoundException("Post Not Found !!!");
        }
    }
    public List<Post> searchByName(String name){
        return repository.findAllByNameContaining(name);
    }

    public void deletePost(Long id){
        this.repository.deleteById(id);
    }

}
