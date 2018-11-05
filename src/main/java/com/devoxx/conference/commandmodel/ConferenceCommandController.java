package com.devoxx.conference.commandmodel;

import com.devoxx.conference.coreapi.AddTalkCommand;
import com.devoxx.conference.coreapi.CreateConferenceCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/command")
public class ConferenceCommandController {

    private final CommandGateway commandGateway;
    private final String conferenceId = UUID.randomUUID().toString();

    public ConferenceCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello Devoxx!";
    }

    @GetMapping
    public void createConference() {
        commandGateway.send(new CreateConferenceCommand(conferenceId, "Devoxx Belgium 2018"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "Ben", "Awesome PCF Stuff"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "Steven", "Awesome Axon Stuff"), LoggingCallback.INSTANCE);
        commandGateway.send(new AddTalkCommand(conferenceId, "Bob", "Clean Coding"), LoggingCallback.INSTANCE);
//        commandGateway.send(new AddTalkCommand(conferenceId, "Allard", "Awesome Axon Stuff"), LoggingCallback.INSTANCE);
    }
}
