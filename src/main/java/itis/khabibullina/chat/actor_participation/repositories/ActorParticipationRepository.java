package itis.khabibullina.chat.actor_participation.repositories;


import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActorParticipationRepository  extends JpaRepository<ActorParticipation, UUID> {
    public Optional<ActorParticipation> findByDialogAndUser(Dialog dialog, User user);
    public List<ActorParticipation> findAllByUser(User user);
}
