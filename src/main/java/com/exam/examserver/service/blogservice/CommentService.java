package com.exam.examserver.service.blogservice;

import com.exam.examserver.model.blog.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, String postedBy, String content);

    List<Comment> getCommentsByPostId(Long postId);
}
