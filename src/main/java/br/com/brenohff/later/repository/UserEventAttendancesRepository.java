package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.model.LTUserEventAttendances;
import br.com.brenohff.later.model.LTUserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEventAttendancesRepository extends JpaRepository<LTUserEventAttendances, LTUserEventPK> {

    @Query("SELECT u FROM LTUser u LEFT JOIN u.attendances att WHERE att.id = :event_id")
    List<LTUser> getAttendancesByEvent(@Param("event_id") Long event_id);
}