package itis.khabibullina.chat.dialog.models;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.message.models.Message;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "dialog")
public class Dialog {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @OneToMany(mappedBy = "dialog")
    private List<ActorParticipation> actorParticipations = new ArrayList<>();

    @OneToMany(mappedBy = "dialog", fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    public Dialog() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ActorParticipation> getActorParticipations() {
        return actorParticipations;
    }

    public void setActorParticipations(List<ActorParticipation> actorParticipations) {
        this.actorParticipations = actorParticipations;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
