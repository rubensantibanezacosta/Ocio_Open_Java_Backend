package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Zones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonesDao extends CrudRepository<Zones, String> {
}
