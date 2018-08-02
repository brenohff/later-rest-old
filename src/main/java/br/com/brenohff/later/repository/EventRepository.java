package br.com.brenohff.later.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brenohff.later.model.LTEvent;

public interface EventRepository extends JpaRepository<LTEvent, Long> {

    @Query("SELECT e FROM LTEvent e WHERE e.user.id = :user_id")
    List<LTEvent> getEventsByUser(@Param("user_id") String user_id);

    @Query("SELECT e FROM LTEvent e WHERE e.isPrivate = false")
    List<LTEvent> getPublic();

}
