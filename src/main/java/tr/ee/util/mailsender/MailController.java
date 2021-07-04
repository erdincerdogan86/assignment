package tr.ee.util.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
public class MailController {

    @Autowired EmailServiceImpl emailService;


    @PostMapping("/mail")
    InternalEmail sendMail(@RequestBody InternalEmail internalEmail ) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<InternalEmail>> violations = validator.validate(internalEmail);
        for (ConstraintViolation<InternalEmail> violation : violations) {
            throw new RuntimeException(violation.getMessage());
        }

        try {
            emailService.sendSimpleMessage(internalEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return  internalEmail;
    }


    @PostMapping("/dataInput")
    DataInput getMyData(@RequestBody DataInput dataInput) {
        dataInput.setName("new name");
        return dataInput;

    }

    @GetMapping("/tester")
    String sendMail( ) {
        return  "Hello spring boot";
    }
}
