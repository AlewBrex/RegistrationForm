import main.model.EmailAddress;
import main.model.EmailContent;
import main.model.User;
import main.repository.UserRepository;
import main.service.RegServiceStub;
import main.service.SendMailerStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegServiceStub.class)
public class RegServiceStubTest
{
    @Autowired
    private RegServiceStub regServiceStub;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SendMailerStub sendMailerStub;

    // тест работы отправки сообщения на почту при вводе корректных данных
    @Test
    public void testReg() throws TimeoutException
    {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setSurname("Surname");
        user.setName("Name");
        user.setPatronymic("Patronymic");
        userRepository.save(user);

        boolean listIsEmpty = regServiceStub.reg(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getSurname(),
                user.getName(),
                user.getPatronymic()).isEmpty();

        Assert.assertTrue(listIsEmpty);

        EmailAddress emailAddress = new EmailAddress(user.getEmail());
        EmailContent emailContent = new EmailContent("Поздравляем с успешной регистрацией в нашем сервисе");

        Mockito.verify(sendMailerStub, Mockito.times(1))
                .sendMail(
                        ArgumentMatchers.eq(emailAddress),
                        ArgumentMatchers.eq(emailContent)
                );
    }

    // тест для проверки заполненности листа ошибок при вводе некорректных данных
    @Test
    public void testRegFail()
    {
        List<String> testErrorsList = new ArrayList<>();
        testErrorsList.add("Что-то пошло не так! Введите email корректно!");

        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email@email.cda");
        user.setSurname("Surname");
        user.setName("Name");
        user.setPatronymic("Patronymic");
        userRepository.save(user);

        List<String> testList = regServiceStub.reg(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getSurname(),
                user.getName(),
                user.getPatronymic());

        Assert.assertEquals(testErrorsList,testList);
    }
}