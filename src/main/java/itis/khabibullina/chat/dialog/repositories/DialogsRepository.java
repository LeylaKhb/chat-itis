package itis.khabibullina.chat.dialog.repositories;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.dialog.models.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DialogsRepository  extends JpaRepository<Dialog, UUID> {
}
