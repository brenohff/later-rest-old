package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brenohff.later.models.LTUser;

public interface UserRepository extends JpaRepository<LTUser, Long> {
	
	@Query("SELECT a "
			+ "FROM LTUser a "
			+ "WHERE a.id = :id")
	public LTUser getUserByID(@Param("id") String id);

}
