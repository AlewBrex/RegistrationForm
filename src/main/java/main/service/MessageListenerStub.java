package main.service;

import lombok.extern.log4j.Log4j2;
import main.model.Message;

@Log4j2
public class MessageListenerStub implements MessageListener
{
    @Override
    public void handleMessage(Message incomingMessage)
    {
        if (incomingMessage.equals(false))
        {
        }
        if (incomingMessage.equals(true))
        {
        }
        log.info("Message type{}", incomingMessage.getMessageType());
    }
}