package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brenohff.later.models.LTChat;

public interface ChatRepository extends JpaRepository<LTChat, Long>{

}
