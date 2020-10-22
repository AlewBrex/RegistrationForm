package main.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegRequest
{
    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
}