package main.service;

import main.model.EmailAddress;
import main.model.EmailContent;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class RegServiceStub implements RegService
{
    private final UserRepository userRepository;
    private final SendMailerStub sendMailerStub;
    
    @Autowired
    public RegServiceStub(UserRepository userRepository,
                          SendMailerStub sendMailerStub)
    {
        this.userRepository = userRepository;
        this.sendMailerStub = sendMailerStub;
    }

    @Override
    public List<String> reg(String login, String password, String email,
                            String surname, String name, String patronymic)
    {
        List<String> registrationErrors = new ArrayList<>();

        if (userRepository.findByLogin(login) || login.isEmpty()
                || login.equals(null))
        {
            registrationErrors.add("Что-то пошло не так! Введите login корректно!");

        }
        if (userRepository.findByPassword(password) || password.isEmpty()
                || password.equals(null))
        {
            registrationErrors.add("Что-то пошло не так! Введите password корректно!");
        }
        if (userRepository.findByEmail(email) || email.isEmpty()
                || email.equals(null) || !email.matches("[\\w\\s\\d]+@([\\w\\s\\d\\u002E])((ru)|(com))"))
        {
            registrationErrors.add("Что-то пошло не так! Введите email корректно!");

        }
        if (userRepository.findByFullName(surname, name, patronymic) || surname.isEmpty()
                || name.isEmpty() || patronymic.isEmpty() || surname.equals(null)
                || name.equals(null) || patronymic.equals(null))
        {
            registrationErrors.add("Что-то пошло не так! Введите surname, " +
                    "name, patronymic корректно!");
        }

        // Если введенные данные удовлетворяют вышеуказанным условиям (ответ из внешней службы), то регистрируем пользователя.
        if (registrationErrors.isEmpty())
        {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setSurname(surname);
            user.setName(name);
            user.setPatronymic(patronymic);
            userRepository.save(user);

            // После регистрации пользователя отправляем на указанный email сообщение об успешной регистрации.
            EmailAddress toAddress = new EmailAddress(email);
            EmailContent messageBody = new EmailContent("Поздравляем с успешной регистрацией в нашем сервисе");
            try
            {
                sendMailerStub.sendMail(toAddress, messageBody);
            } catch (TimeoutException e)
            {
                e.printStackTrace();
            }
        }
        return registrationErrors;
    }
}