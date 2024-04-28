package itis.khabibullina.chat.message.models;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.dialog.models.Dialog;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    @Column(name = "text_context")
    private String textContext;

    @ManyToOne
    @JoinColumn(name = "actor_participation_id")
    private ActorParticipation actorParticipation;

    public Message(Dialog dialog, String textContext, ActorParticipation actorParticipation) {
        this.dialog = dialog;
        this.textContext = textContext;
        this.actorParticipation = actorParticipation;
    }

    public Message() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    public ActorParticipation getActorParticipation() {
        return actorParticipation;
    }

    public void setActorParticipation(ActorParticipation actorParticipation) {
        this.actorParticipation = actorParticipation;
    }
}
