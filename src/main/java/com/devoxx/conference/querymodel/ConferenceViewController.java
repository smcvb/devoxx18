package com.devoxx.conference.querymodel;

import com.devoxx.conference.coreapi.FindAllConferencesQuery;
import com.devoxx.conference.coreapi.FindSpeakerByName;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query")
public class ConferenceViewController {

    private final QueryGateway queryGateway;

    public ConferenceViewController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/conferences")
    public CompletableFuture<List<ConferenceView>> conferences() {
        return queryGateway.query(new FindAllConferencesQuery(), ResponseTypes.multipleInstancesOf(ConferenceView.class));
    }

    @GetMapping("/speaker/{speaker}")
    public CompletableFuture<SpeakerView> speaker(@PathVariable String speaker) {
        return queryGateway.query(new FindSpeakerByName(speaker), ResponseTypes.instanceOf(SpeakerView.class));
    }
}
