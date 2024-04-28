package itis.khabibullina.chat.controllers.params;



import java.util.List;
import java.util.UUID;

public class DialogDTO {
    private UUID id;
    private List<MessageDto> messages;

    public DialogDTO(UUID id, List<MessageDto> messages) {
        this.id = id;
        this.messages = messages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }
}
