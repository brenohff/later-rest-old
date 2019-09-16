package br.com.brenohff.later.service;

import br.com.brenohff.later.model.*;
import br.com.brenohff.later.repository.UserEventAttendancesRepository;
import br.com.brenohff.later.repository.UserEventFavoritesRepository;
import br.com.brenohff.later.service.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventService {

    @Autowired
    UserEventAttendancesRepository attendancesRepository;

    @Autowired
    UserEventFavoritesRepository favoritesRepository;

    public List<LTUser> getAttendancesByEvent(Long event_id) {
        return attendancesRepository.getAttendancesByEvent(event_id);
    }

    public List<LTEvent> getFavoritesEventsByUser(String user_id) {
        return favoritesRepository.getFavoritesEventsByUser(user_id);
    }

    public void saveFavoritesEvents(Long event_id, String user_id) {
        favoritesRepository.save(new LTUserEventFavorites(event_id, user_id));
    }

    public void deleteFavoritesEvents(Long event_id, String user_id) {
        try {
            favoritesRepository.delete(favoritesRepository.findOne(new LTUserEventPK(event_id, user_id)));
        } catch (Exception e) {
            throw new ObjectNotFound("Não foi possível excluir este favorito.");
        }
    }

    public void saveAttendances(Long event_id, String user_id) {
        attendancesRepository.save(new LTUserEventAttendances(event_id, user_id));
    }

    public void deleteAttendances(Long event_id, String user_id) {
        try {
            attendancesRepository.delete(attendancesRepository.findOne(new LTUserEventPK(event_id, user_id)));
        } catch (Exception e) {
            throw new ObjectNotFound("Não foi possível excluir este favorito.");
        }
    }
}
