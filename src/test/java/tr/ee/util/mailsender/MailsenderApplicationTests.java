package tr.ee.util.mailsender;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import javax.mail.internet.MimeMessage;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailsenderApplicationTests {

	@Container
	static GenericContainer greenMailContainer = new GenericContainer<>(DockerImageName.parse("greenmail/standalone:1.6.1"))
			.withEnv("GREENMAIL_OPTS", "-Dgreenmail.setup.test.all -Dgreenmail.hostname=0.0.0.0 -Dgreenmail.users=duke:springboot")
			.withExposedPorts(3025);

	@DynamicPropertySource
	static void configureMailHost(DynamicPropertyRegistry registry) {
		registry.add("spring.mail.host", greenMailContainer::getHost);
		registry.add("spring.mail.port", greenMailContainer::getFirstMappedPort);
	}


	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseURL = "http://localhost:8080/";

	@Test
	void contextLoads() {
		System.out.println("test star...");
		UUID uuid = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		InternalEmail internalEmail = new InternalEmail("erdinc.erdogan@hotmail.com", "erdinc.erdogan@hotmail.com", "erdinc.erdogan@hotmail.com", "TEST AMAÇLI MAIL GONDERİMİ",
				"<body>...<br/> ... </body>", new ArrayList<UUID>(){{ add(uuid); add(uuid2); }} ,"Erdinç" , "Erdoğan" );


		DataInput dataInput = new DataInput("erdinç", "erdoğan");

		//System.out.println(restTemplate.postForObject(baseURL+"dataInput", dataInput, DataInput.class));

		System.out.println(restTemplate.postForObject(baseURL+"mail", internalEmail, InternalEmail.class));

	/*
        await().atMost(2, SECONDS).untilAsserted(() -> {
            MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
            assertEquals(1, receivedMessages.length);

            MimeMessage receivedMessage = receivedMessages[0];
            assertEquals("Hello World!", GreenMailUtil.getBody(receivedMessage));
            assertEquals(1, receivedMessage.getAllRecipients().length);
            assertEquals("duke@spring.io", receivedMessage.getAllRecipients()[0].toString());
        });
	*/

	}


}
