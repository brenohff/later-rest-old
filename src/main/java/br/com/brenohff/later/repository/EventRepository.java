package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<LTEvent, Long> {

    @Query("SELECT e FROM LTEvent e WHERE e.users.id = :users_id")
    List<LTEvent> getEventsByUser(@Param("users_id") String users_id);

    @Query("SELECT e FROM LTEvent e WHERE e.isPrivate = false")
    List<LTEvent> getPublic();

    @Query("DELETE FROM LTEvent e WHERE e.id = :event_id")
    void deleteById(@Param("event_id") Long event_id);

}
