package com.devoxx.conference.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateConferenceCommand(
        val conferenceId: String,
        val name: String
)

data class AddTalkCommand(
        @TargetAggregateIdentifier val conferenceId: String,
        val speaker: String,
        val topic: String
)

data class TalkAddedEvent(
        val conferenceId: String,
        val speaker: String,
        val talkTitle: String
)

data class ConferenceCreatedEvent(
        val conferenceId: String,
        val name: String
)

class DuplicateTalkException : Exception()

class FindAllConferencesQuery

data class FindSpeakerByName(val speakerName: String)