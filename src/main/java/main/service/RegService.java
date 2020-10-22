package main.service;

import java.util.List;

public interface RegService
{
    List<String> reg(String login, String password, String email,
                    String surname, String name, String patronymic);
}