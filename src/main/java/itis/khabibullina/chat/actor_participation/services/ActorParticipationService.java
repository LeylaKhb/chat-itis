package itis.khabibullina.chat.actor_participation.services;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.actor_participation.repositories.ActorParticipationRepository;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.user.models.User;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ActorParticipationService {
    private final ActorParticipationRepository actorParticipationRepository;

    public ActorParticipationService(ActorParticipationRepository actorParticipationRepository) {
        this.actorParticipationRepository = actorParticipationRepository;
    }

    public ActorParticipation createActorParticipation(Dialog dialog, User user) {
        ActorParticipation actorParticipation = new ActorParticipation(dialog, user);
        return actorParticipationRepository.save(actorParticipation);
    }

    public ActorParticipation findByDialogAndUser(Dialog dialog, User user) throws UserPrincipalNotFoundException {
        Optional<ActorParticipation> optionalActorParticipation = actorParticipationRepository.findByDialogAndUser(dialog, user);
        if (optionalActorParticipation.isEmpty())
            throw new UserPrincipalNotFoundException("no such a dialog with such a user");
        return optionalActorParticipation.get();
    }

    public void addMessage(ActorParticipation actorParticipation, Message message) {
        actorParticipation.getMessages().add(message);
        actorParticipationRepository.save(actorParticipation);
    }

    public List<ActorParticipation> findAllByUser(User user) {
        return actorParticipationRepository.findAllByUser(user);
    }

}
