package main.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import main.model.EmailAddress;
import main.model.EmailContent;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Log4j2
@Service
public class SendMailerStub implements SendMailer
{
    public void sendMail(EmailAddress toAddress, EmailContent messageBody)
            throws TimeoutException
    {
        if (shouldThrowTimeout())
        {
            sleep();
            throw new TimeoutException("Timeout!");
        }
        if (shouldSleep())
        {
            sleep();
        }
        log.info("Message sent to {}, body {}", toAddress, messageBody);
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