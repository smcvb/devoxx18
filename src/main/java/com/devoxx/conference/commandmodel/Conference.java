package com.devoxx.conference.commandmodel;

import com.devoxx.conference.coreapi.AddTalkCommand;
import com.devoxx.conference.coreapi.ConferenceCreatedEvent;
import com.devoxx.conference.coreapi.CreateConferenceCommand;
import com.devoxx.conference.coreapi.DuplicateTalkException;
import com.devoxx.conference.coreapi.TalkAddedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class Conference {

    @AggregateIdentifier
    private String conferenceId;
    private List<String> talks;

    public Conference() {
    }

    @CommandHandler
    public Conference(CreateConferenceCommand cmd) {
        AggregateLifecycle.apply(new ConferenceCreatedEvent(cmd.getConferenceId(), cmd.getName()));
    }

    @CommandHandler
    public void handle(AddTalkCommand command) throws DuplicateTalkException {
        String topic = command.getTopic();
        if (talks.contains(topic)) {
            throw new DuplicateTalkException();
        }

        AggregateLifecycle.apply(new TalkAddedEvent(conferenceId, command.getSpeaker(), topic));
    }

    @EventSourcingHandler
    public void on(ConferenceCreatedEvent event) {
        conferenceId = event.getConferenceId();
        talks = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(TalkAddedEvent event) {
        talks.add(event.getTalkTitle());
    }
}
