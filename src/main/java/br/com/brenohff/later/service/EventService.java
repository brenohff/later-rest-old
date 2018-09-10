package br.com.brenohff.later.service;

import br.com.brenohff.later.enums.EventStatus;
import br.com.brenohff.later.model.LTCategory;
import br.com.brenohff.later.model.LTCategoryEvent;
import br.com.brenohff.later.model.LTEvent;
import br.com.brenohff.later.repository.CategoryEventRepository;
import br.com.brenohff.later.repository.EventRepository;
import br.com.brenohff.later.repository.UserRepository;
import br.com.brenohff.later.service.exceptions.ObjectNotFound;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryEventRepository categoryEventRepository;

    @Autowired
    S3Service s3Service;

    public void saveEvent(String e, MultipartFile file) {
        LTEvent event = new Gson().fromJson(e, LTEvent.class);

        event.setDt_post(new Date());
        event.setImage(s3Service.uploadFile(file).toString());
        eventRepository.save(event);
        for (LTCategory category : event.getCategories()) {
            categoryEventRepository.save(new LTCategoryEvent(category.getId(), event.getId()));
        }
    }

    public List<LTEvent> getPublic() {
        return eventRepository.getPublic();
    }

    public List<LTEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    public LTEvent getEventById(Long event_id) {
        LTEvent event = eventRepository.findOne(event_id);

        if (event != null) {
            return event;
        } else {
            throw new ObjectNotFound("Evento não encontrado");
        }
    }

    public List<LTEvent> getEventsByUser(String user_id) {

        List<LTEvent> lt_events = eventRepository.getEventsByUser(user_id);

        if (!lt_events.isEmpty()) {
            return lt_events;
        } else {
            throw new ObjectNotFound("Nenhum evento encontrado.");
        }
    }

    public void changeEventStatus(EventStatus eventStatus) {
        eventRepository.changeEventStatus(eventStatus);
    }

}
