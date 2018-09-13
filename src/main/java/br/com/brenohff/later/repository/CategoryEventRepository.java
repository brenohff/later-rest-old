package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTCategoryEvent;
import br.com.brenohff.later.model.LTCategoryEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryEventRepository extends JpaRepository<LTCategoryEvent, LTCategoryEventPK> {

    @Query("SELECT cat FROM LTCategoryEvent cat WHERE cat.event_id = :event_id")
    List<LTCategoryEvent> getLTCategoryEventByEventId(@Param("event_id") Long event_id);

}