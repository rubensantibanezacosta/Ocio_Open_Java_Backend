package com.ocio.backend17.services;

import com.ocio.backend17.entities.Images;

import java.util.List;
import java.util.Optional;

public interface IImages {
    Optional<Images> getById(int id);

    List<Images> getAll();

    int deleteById(int id);

    Images updloadImage(Images image);

}
