package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTEvent;
import br.com.brenohff.later.model.LTUserEventFavorites;
import br.com.brenohff.later.model.LTUserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEventFavoritesRepository extends JpaRepository<LTUserEventFavorites, LTUserEventPK> {

    @Query("SELECT e FROM LTEvent e LEFT JOIN e.favorites f WHERE f.id = :user_id")
    List<LTEvent> getFavoritesEventsByUser(@Param("user_id") String user_id);

}