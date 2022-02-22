package com.ocio.backend17.websocket;

import com.ocio.backend17.dto.CommentDeleteDto;
import com.ocio.backend17.entities.Comments;
import com.ocio.backend17.services.CommentsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    CommentsImpl commentsImpl;

    @MessageMapping("/message")
    public void newComment(Comments comment) {
  
        Comments savedComment = commentsImpl.addComment(comment);
        Comments commentToSend = commentsImpl.findbyId(savedComment.getComment_id()).get();
        simpMessagingTemplate.convertAndSend("/comments-chat/" + (int) comment.getEvent_id(), commentToSend);
    }

    @MessageMapping("/message_delete")
    
    public void newCommentDelete(CommentDeleteDto commentDelete) {
        if (commentsImpl.findbyId(commentDelete.getId()).isPresent()) {
            Comments commentDeleted=commentsImpl.findbyId(commentDelete.getId()).get();
            commentsImpl.deleteById(commentDelete.getId());
            simpMessagingTemplate.convertAndSend(
                    "/comments-chat/delete_" + (int) commentDeleted.getEvent_id(),
                    commentDelete.getIndex());
        }
    }

}
