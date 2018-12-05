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

    @Query(value = "SELECT * FROM event e WHERE e.is_private = false AND to_date(e.date, 'dd/mm/yyyy') < CURRENT_DATE AND e.status = 1", nativeQuery = true)
    List<LTEvent> getEventsActivesAndPublic();

    @Transactional
    @Modifying
    @Query("UPDATE LTEvent e SET e.status = :eventStatus")
    void changeEventStatus(@Param("eventStatus") EventStatus eventStatus);

}
