package br.com.brenohff.later.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brenohff.later.models.LTChat;

public interface ChatRepository extends JpaRepository<LTChat, Long>{
	
	@Query("SELECT c FROM LTChat c WHERE c.eventId = :eventId")
	public List<LTChat> getChatByEventId(@Param("eventId") String eventId);

}
