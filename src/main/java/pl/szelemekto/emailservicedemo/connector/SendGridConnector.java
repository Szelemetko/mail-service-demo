package pl.szelemekto.emailservicedemo.connector;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import pl.szelemekto.emailservicedemo.model.EmailMsg;

@Component
public class SendGridConnector implements EmailProviderConnector {

	// private static final Logger log =
	// LoggerFactory.getLogger(SendGridConnector.class);
	@Value("${sendgrid.key}")
	private String apiKey;

	@Override
	public void send(EmailMsg email) throws IOException {

		Email from = new Email(email.getFrom());
		String subject = email.getSubject();
		Email to = new Email(email.getTo());
		Content content = new Content("text/plain", email.getBody());
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		sg.api(request);

	}

}
