package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Comments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao extends CrudRepository<Comments, Long> {
    @Query("Select u from Comments u where u.event_id=?1")
    List<Comments> findByEvent_id(Double event_id);
}
