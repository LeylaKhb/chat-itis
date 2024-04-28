package itis.khabibullina.chat.controllers.params;

public class MessageDto {
    private String textContext;
    private String firstName;

    public MessageDto(String textContext, String firstName) {
        this.textContext = textContext;
        this.firstName = firstName;
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
