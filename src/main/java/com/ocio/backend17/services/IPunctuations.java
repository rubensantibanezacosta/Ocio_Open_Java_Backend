package com.ocio.backend17.services;

import com.ocio.backend17.entities.Punctuations;
import com.ocio.backend17.entities.PunctuationsPK;

import java.util.List;
import java.util.Optional;

public interface IPunctuations {
    Punctuations createOrUpdate(Punctuations punctuation);

    List<Punctuations> findAll();

    List<Punctuations> findByEvent(Double event_id);

    List<Punctuations> findByOrganizer(String email);

    Optional<Punctuations> findByPK(PunctuationsPK punctuationsPK);

    int deleteByPk(PunctuationsPK punctuationsPK);

}
