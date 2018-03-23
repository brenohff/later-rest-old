package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brenohff.later.models.LTEvent;

public interface EventRepository extends JpaRepository<LTEvent, Long> {

}
