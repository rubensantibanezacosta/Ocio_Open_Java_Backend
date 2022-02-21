package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesDao extends CrudRepository<Images, Integer> {
}
