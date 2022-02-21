package com.ocio.backend17.dao;

import com.ocio.backend17.entities.Events;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EventsDao extends CrudRepository<Events, Double> {
    @Query("Select u from Events u order by u.date desc")
    List<Events> findAllOrderDesc();

    @Query("Select u from Events u order by u.date asc")
    List<Events> findAllOrderAsc();

    @Query("Select u from Events u where u.date between ?1 and ?2")
    List<Events> findAllByDate(Timestamp dateStart, Timestamp dateEnd);

    List<Events> findAllByOrganizerOrderByDateAsc(String organizer);

    List<Events> findAllByOrganizerOrderByDateDesc(String organizer);

    List<Events> findAllByZone(String id);

}
