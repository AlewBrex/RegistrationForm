package main.service;

import main.model.EmailAddress;
import main.model.EmailContent;

import java.util.concurrent.TimeoutException;

public interface SendMailer
{
    void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}