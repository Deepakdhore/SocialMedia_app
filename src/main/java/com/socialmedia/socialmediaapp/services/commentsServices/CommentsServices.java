package com.socialmedia.socialmediaapp.services.commentsServices;

import com.socialmedia.socialmediaapp.entities.Comments;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentsServices {
    public Comments addComment(Comments comment);
    public List<Comments> getAllComments();
    public List<Comments> getByUserId(Long userId);
    public List<Comments> getByPostId(Long PostId);
    public List<Comments> getByUserIdPostId(Long userId,Long PostId);
}
