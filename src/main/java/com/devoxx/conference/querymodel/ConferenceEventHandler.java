package com.devoxx.conference.querymodel;

import com.devoxx.conference.coreapi.ConferenceCreatedEvent;
import com.devoxx.conference.coreapi.FindAllConferencesQuery;
import com.devoxx.conference.coreapi.FindSpeakerByName;
import com.devoxx.conference.coreapi.TalkAddedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConferenceEventHandler {

    private final ConferenceViewRepository conferenceViewRepository;
    private final SpeakerViewRepository speakerViewRepository;

    public ConferenceEventHandler(ConferenceViewRepository conferenceViewRepository,
                                  SpeakerViewRepository speakerViewRepository) {
        this.conferenceViewRepository = conferenceViewRepository;
        this.speakerViewRepository = speakerViewRepository;
    }

    @EventHandler
    public void on(ConferenceCreatedEvent event) {
        conferenceViewRepository.save(new ConferenceView(event.getConferenceId(), event.getName()));
    }

    @EventHandler
    public void on(TalkAddedEvent event) {
        String speaker = event.getSpeaker();

        List<String> talks = speakerViewRepository.findById(speaker)
                                                  .map(SpeakerView::getTalks)
                                                  .orElse(new ArrayList<>());

        talks.add(event.getTalkTitle());
        speakerViewRepository.save(new SpeakerView(speaker, talks));
    }


    @QueryHandler
    public List<ConferenceView> queryConferences(FindAllConferencesQuery query) {
        return conferenceViewRepository.findAll();
    }

    @QueryHandler
    public SpeakerView querySpeaker(FindSpeakerByName query) {
        return speakerViewRepository.getOne(query.getSpeakerName());
    }
}
