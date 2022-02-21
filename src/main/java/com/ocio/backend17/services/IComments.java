package com.ocio.backend17.services;

import com.ocio.backend17.entities.Comments;

import java.util.List;
import java.util.Optional;

public interface IComments {
    Comments addComment(Comments comment);

    List<Comments> findByEventId(Double event_id);

    int deleteById(long id);

    Optional<Comments> findbyId(long id);
}
