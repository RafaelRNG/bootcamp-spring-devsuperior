package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = this.eventRepository.findAll(pageable);

        return page.map(event -> new EventDTO(event));
    }

    public EventDTO insert(EventDTO eventDTO) {

        Optional<City> city = cityRepository.findById(eventDTO.getCityId());

        Event event = new Event(eventDTO.getId(), eventDTO.getName(), eventDTO.getDate(), eventDTO.getUrl(), city.orElseThrow());

        event = this.eventRepository.save(event);

        return new EventDTO(event);
    }
}