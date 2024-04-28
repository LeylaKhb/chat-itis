package itis.khabibullina.chat.message.repositories;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.message.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MessageRepository  extends JpaRepository<Message, UUID> {
    public List<Message> findAllByDialog(Dialog dialog);
}
