package ucr.ac.C12599;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ucr.ac.C12599.handlers.RegisterUserHandler;
import ucr.ac.C12599.handlers.impl.RegisterUserHandlerImpl;

@RestController
public class HelloController {

    @Autowired
    private RegisterUserHandler handler;

    @GetMapping("/hello")
    public String hello() {
        var command = new RegisterUserHandler.Command(
                "Mariano",
                "marianoda20027@gmail.com",
                "password"
        );

        RegisterUserHandler.Result result = handler.registerUser(
                command
        );


        return switch (result) {
            case RegisterUserHandler.Result.Success success -> success.message();
            case RegisterUserHandler.Result.InvalidData invalidData ->
                    "Invalid data: " + String.join(", ", invalidData.fields());
            case RegisterUserHandler.Result.EmailAlreadyExists emailAlreadyExists -> null;
        };
    }

}
