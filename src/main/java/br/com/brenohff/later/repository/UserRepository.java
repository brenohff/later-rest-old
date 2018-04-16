package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brenohff.later.models.LTUser;

public interface UserRepository extends JpaRepository<LTUser, String> {
	
	@Query("SELECT a "
			+ "FROM LTUser a "
			+ "WHERE a.face_id = :face_id")
	public LTUser getUserByID(@Param("face_id") String id);

}
