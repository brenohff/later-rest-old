package br.com.brenohff.later.repository;

import br.com.brenohff.later.model.LTUserEventFavorites;
import br.com.brenohff.later.model.LTUserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventFavoritesRepository extends JpaRepository<LTUserEventFavorites, LTUserEventPK> {

}