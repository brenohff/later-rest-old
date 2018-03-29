package br.com.brenohff.later.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brenohff.later.models.LTComment;

public interface CommentRepository extends JpaRepository<LTComment, Long> {

	@Query("SELECT e FROM LTComment e WHERE e.user.id = :user_id")
	public List<LTComment> getCommentsByUser(@Param("user_id") Long user_id);

	@Query("SELECT e FROM LTComment e WHERE e.user.id = :user_id AND e.event.id = :event_id")
	public List<LTComment> getCommentsByUserAndEvent(@Param("user_id") Long user_id, @Param("event_id") Long event_id);

}
