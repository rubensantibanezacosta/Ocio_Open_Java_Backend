package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.PunctuationsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunctuationsDao extends CrudRepository<Punctuations, PunctuationsPK> {
    @Query("Select u from Punctuations u where u.event_id=?1")
    List<Punctuations> findPunctuationsByEvent_id(Double event_id);

}
