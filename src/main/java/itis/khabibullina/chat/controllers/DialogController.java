package itis.khabibullina.chat.controllers;


import itis.khabibullina.chat.controllers.params.DialogDTO;
import itis.khabibullina.chat.controllers.params.MessageDto;
import itis.khabibullina.chat.dialog.models.Dialog;
import itis.khabibullina.chat.dialog.services.DialogService;
import itis.khabibullina.chat.message.models.Message;
import itis.khabibullina.chat.message.services.MessageService;
import itis.khabibullina.chat.user.models.User;
import itis.khabibullina.chat.user.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class DialogController {
    private final DialogService dialogService;
    private final MessageService messageService;
    private final UserService userService;

    public DialogController(DialogService dialogService, MessageService messageService, UserService userService) {
        this.dialogService = dialogService;
        this.messageService = messageService;
        this.userService = userService;
    }


    @PostMapping("/create")
    public String createDialog() {
        Dialog dialog = dialogService.createDialog();
        return dialog.getId().toString();
    }

    @GetMapping("/dialogs")
    public List<Dialog> getDialogs() {
        return dialogService.findAllDialogs();
    }

    @GetMapping("/dialog/{id}")
    public DialogDTO getDialog(@PathVariable("id") String id) {
        Dialog dialog = dialogService.findById(UUID.fromString(id));
        List<Message> messages = messageService.findAllByDialogId(dialog);
        List<MessageDto> messagesDto = messages.stream()
                .map(message -> new MessageDto(message.getTextContext(), message.getActorParticipation().getUser().getFirstName()))
                .collect(Collectors.toList());
        System.out.println(dialog);
        System.out.println(messages);
        return new DialogDTO(dialog.getId(), messagesDto);
    }

    @MessageMapping("/dialog/{id}")
    public String processMessage(String message, @PathVariable("id") String id) throws UserPrincipalNotFoundException {
        User user = userService.getUser();
        messageService.create(message, id);
        return String.format("[%s] %s", user.getFirstName(), message);
//        Dialog dialog = dialogService.findById(UUID.fromString(id));
//        List<Message> messages = messageService.findAllByDialogId(dialog);
//        List<MessageDto> messagesDto = messages.stream()
//                .map(message -> new MessageDto(message.getTextContext(), message.getActorParticipation().getUser().getFirstName()))
//                .collect(Collectors.toList());
//        System.out.println(dialog);
//        System.out.println(messages);
//        return new DialogDTO(dialog.getId(), messagesDto);
    }
}
