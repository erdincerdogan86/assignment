package tr.ee.util.mailsender;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    Configuration freemarkerConfiguration;



    public void sendSimpleMessage(InternalEmail internalEmail) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        helper.setFrom(internalEmail.getFrom());
        helper.setTo(internalEmail.getTo());
        helper.setSubject(internalEmail.getSubject());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", internalEmail.getFirstName());
        model.put("username", internalEmail.getLastName());
        helper.setText(geFreeMarkerTemplateContent(model , "email.ftlh"));
        helper.addInline("leftSideImage", new ClassPathResource("SpringSource-logo.jpg"));
        emailSender.send(message);
    }


    private String geFreeMarkerTemplateContent(Map<String, Object> model, String templateName) {
        StringBuffer content = new StringBuffer();
        freemarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(templateName), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }
}