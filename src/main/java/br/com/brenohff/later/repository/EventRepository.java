package br.com.brenohff.later.repository;

import br.com.brenohff.later.enums.EventStatus;
import br.com.brenohff.later.model.LTEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EventRepository extends JpaRepository<LTEvent, Long> {

    @Query("SELECT e FROM LTEvent e WHERE e.users.id = :users_id")
    List<LTEvent> getEventsByUser(@Param("users_id") String users_id);

    @Query(value = "SELECT * FROM event e WHERE e.is_private = false AND to_date(e.date, 'dd/mm/yyyy') >= CURRENT_DATE AND e.status = 1 ORDER BY to_date(e.date, 'dd/mm/yyyy') ASC", nativeQuery = true)
    List<LTEvent> getEventsActivesAndPublic();

    @Query("SELECT e FROM LTEvent e WHERE e.status = 0")
    List<LTEvent> getPendingEvents();

    @Query("SELECT e FROM LTEvent e LEFT JOIN e.categories ce WHERE ce.id = :category_id")
    List<LTEvent> getEventsByCategory(@Param("category_id") Long category_id);

    @Transactional
    @Modifying
    @Query("UPDATE LTEvent e SET e.status = :eventStatus WHERE e.id = :event_id ")
    void changeEventStatus(@Param("eventStatus") EventStatus eventStatus, @Param("event_id") Long event_id);

}
