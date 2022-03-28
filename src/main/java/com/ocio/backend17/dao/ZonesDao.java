package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Users;
import com.ocio.backend17.entities.Zones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZonesDao extends CrudRepository<Zones, String> {
    @Query("Select u from Zones u order by u.punctuationavg desc")
    List<Zones> findZonesOrderByPunctuacion();
}
