package main.service;

import lombok.SneakyThrows;
import main.model.Message;
import main.model.MessageId;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MessagingServiceStub implements MessagingService
{
    @Override
    public <T> MessageId send(Message<T> msg)
    {
        return new MessageId(UUID.randomUUID());
    }

    @Override
    public <T> Message<T> receive(MessageId messageId, Class<T> messageType)
            throws TimeoutException
    {
        if (shouldThrowTimeout())
        {
            sleep();
        }
        if (shouldSleep())
        {
            sleep();
        }
        return null;
    }

    @SneakyThrows
    private static void sleep()
    {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    private static boolean shouldSleep()
    {
        return new Random().nextInt(10) == 1;
    }

    private static boolean shouldThrowTimeout()
    {
        return new Random().nextInt(10) == 1;
    }
}