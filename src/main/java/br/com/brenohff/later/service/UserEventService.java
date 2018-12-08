package br.com.brenohff.later.service;

import br.com.brenohff.later.model.LTEvent;
import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.repository.UserEventAttendancesRepository;
import br.com.brenohff.later.repository.UserEventFavoritesRepository;
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

}
