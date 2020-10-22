import main.controllers.ApiGeneralController;
import main.service.RegServiceStub;
import main.service.SendMailerStub;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiGeneralController.class)
public class ApiGeneralControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegServiceStub regServiceStub;

    @MockBean
    private SendMailerStub sendMailerStub;
}