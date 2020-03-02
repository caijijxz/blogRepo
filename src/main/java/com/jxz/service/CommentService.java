package com.jxz.service;

import com.jxz.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment(Long id);
}
