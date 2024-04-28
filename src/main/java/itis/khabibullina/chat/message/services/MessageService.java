package itis.khabibullina.chat.message.services;

import itis.khabibullina.chat.actor_participation.models.ActorParticipation;
import itis.khabibullina.chat.actor_participation.services.ActorParticipationService;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.dialog.services.DialogService;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.message.repositories.MessageRepository;
import itis.khabibullina.chat.user.models.User;
import itis.khabibullina.chat.user.services.UserService;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private DialogService dialogService;
    private UserService userService;
    private ActorParticipationService actorParticipationService;

    public MessageService(MessageRepository messageRepository, DialogService dialogService, UserService userService, ActorParticipationService actorParticipationService) {
        this.messageRepository = messageRepository;
        this.dialogService = dialogService;
        this.userService = userService;
        this.actorParticipationService = actorParticipationService;
    }

    public Message create(String text, String dialogId) throws UserPrincipalNotFoundException {
        System.out.println(dialogId);
        Dialog dialog = dialogService.findById(UUID.fromString(dialogId));
        User user = userService.getUser();
        ActorParticipation actorParticipation = actorParticipationService.findByDialogAndUser(dialog, user);

        Message message = new Message(dialog, text, actorParticipation);
        Message savedMessage = messageRepository.save(message);
        actorParticipationService.addMessage(actorParticipation, savedMessage);

        Dialog updatedDialog = dialogService.addMessage(dialog, savedMessage);
        System.out.println("new " + updatedDialog);
        return savedMessage;
    }

    public List<Message> findAllByDialogId(Dialog dialog) {
        return messageRepository.findAllByDialog(dialog);
    }
}
