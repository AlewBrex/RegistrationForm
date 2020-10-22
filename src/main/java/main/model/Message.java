package main.model;

import lombok.Data;

@Data
public class Message<T>
{
    private MessageId id;
    private T messageType;

    public Message(T messageType)
    {
        this.messageType = messageType;
    }

    public Message(MessageId id, T messageType)
    {
        this.id = id;
        this.messageType = messageType;
    }
}