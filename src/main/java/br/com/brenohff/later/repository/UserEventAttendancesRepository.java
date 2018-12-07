package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTUserEventAttendances;
import br.com.brenohff.later.model.LTUserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventAttendancesRepository extends JpaRepository<LTUserEventAttendances, LTUserEventPK> {

}