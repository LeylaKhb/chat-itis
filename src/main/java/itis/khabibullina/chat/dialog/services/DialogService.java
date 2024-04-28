package itis.khabibullina.chat.dialog.services;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.actor_participation.services.ActorParticipationService;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.dialog.repositories.DialogsRepository;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.user.services.UserService;
import org.springframework.stereotype.Service;
import itis.khabibullina.chat.user.models.User;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class DialogService {
    private final DialogsRepository dialogsRepository;

    private final ActorParticipationService actorParticipationService;

    private final UserService userService;

    public DialogService(DialogsRepository dialogsRepository, ActorParticipationService actorParticipationService, UserService userService) {
        this.dialogsRepository = dialogsRepository;
        this.actorParticipationService = actorParticipationService;
        this.userService = userService;
    }
    public Dialog createDialog() {
        Dialog dialog =  dialogsRepository.save(new Dialog());
        User user = userService.getUser();
        return addUser(dialog, user);
    }

    public Dialog addUser(Dialog dialog, User user) {
        ActorParticipation actorParticipation = actorParticipationService.createActorParticipation(dialog, user);
        Dialog dbDialog = findById(dialog.getId());
        dbDialog.getActorParticipations().add(actorParticipation);
        return dialogsRepository.save(dbDialog);
    }

    public Dialog findById(UUID id) {
        Optional<Dialog> optionalDialog = dialogsRepository.findById(id);
        if (optionalDialog.isEmpty()) {
            throw new RuntimeException(String.format("dialog with id %s not found", id));
        }
        return optionalDialog.get();
    }

    public Dialog addMessage(Dialog dialog, Message message) {
        System.out.println(dialog.toString() + " " + message);
        dialog.getMessages().add(message);
        System.out.println(dialog.getMessages());
        return dialogsRepository.save(dialog);
    }

    public List<Dialog> findAllDialogs() {
        User user = userService.getUser();
        List<ActorParticipation> actorParticipations = actorParticipationService.findAllByUser(user);
        return actorParticipations.stream()
                .map(ActorParticipation::getDialog)
                .collect(Collectors.toList());
    }
}
