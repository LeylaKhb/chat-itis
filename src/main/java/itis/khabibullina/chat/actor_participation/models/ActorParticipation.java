package itis.khabibullina.chat.actor_participation.models;

import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.user.models.User;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "actor_participation")
public class ActorParticipation {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "actorParticipation")
    private List<Message> messages = new ArrayList<>();

    public ActorParticipation(Dialog dialog, User user) {
        this.dialog = dialog;
        this.user = user;
    }

    public ActorParticipation() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
