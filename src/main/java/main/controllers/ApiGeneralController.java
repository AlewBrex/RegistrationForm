package main.controllers;

import main.api.request.RegRequest;
import main.api.response.SuccessResponse;
import main.service.RegServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiGeneralController
{
    private RegServiceStub regServiceStub;

    @Autowired
    public ApiGeneralController(RegServiceStub regServiceStub)
    {
        this.regServiceStub = regServiceStub;
    }

    @PostMapping("/users/")
    public ResponseEntity<SuccessResponse> successResp(@RequestBody RegRequest regRequest)
    {
        String login = regRequest.getLogin();
        String password = regRequest.getPassword();
        String email = regRequest.getEmail();
        String surname = regRequest.getSurname();
        String name = regRequest.getName();
        String patronymic = regRequest.getPatronymic();

        List<String> regInformList = regServiceStub.reg(login, password, email,
                surname, name, patronymic);
        if (regInformList.isEmpty())
        {
            SuccessResponse successResponse = new SuccessResponse(true, "Good job");
            return ResponseEntity.ok(successResponse);
        }

        SuccessResponse response = new SuccessResponse(false, regInformList.toString());
        return ResponseEntity.badRequest().body(response);
    }
}