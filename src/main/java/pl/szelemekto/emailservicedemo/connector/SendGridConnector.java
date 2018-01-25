package pl.szelemekto.emailservicedemo.connector;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import pl.szelemekto.emailservicedemo.exception.EmailServiceConnectorException;
import pl.szelemekto.emailservicedemo.model.EmailAddress;
import pl.szelemekto.emailservicedemo.model.EmailMsg;

@Component
public class SendGridConnector implements EmailProviderConnector {

	@Value("${sendgrid.key}")
	private String apiKey;

	@Override
	public void send(EmailMsg email) throws EmailServiceConnectorException {

		Mail mail = new Mail();

		Email from = createSendGridEmailAddress(email.getFrom());
		mail.setFrom(from);

		String subject = email.getSubject();
		mail.setSubject(subject);

		Content content = new Content("text/plain", email.getBody());
		mail.addContent(content);

		Personalization personalization = new Personalization();

		for (EmailAddress emailAddress : email.getTo()) {
			personalization.addTo(createSendGridEmailAddress(emailAddress));
		}

		if (email.getCc() != null) {
			for (EmailAddress emailAddress : email.getCc()) {
				personalization.addTo(createSendGridEmailAddress(emailAddress));
			}
		}

		if (email.getCc() != null) {
			for (EmailAddress emailAddress : email.getBcc()) {
				personalization.addTo(createSendGridEmailAddress(emailAddress));
			}
		}

		mail.addPersonalization(personalization);

		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");

		try {
			request.setBody(mail.build());
			Response response = sg.api(request);
			if(response.getStatusCode() >= 300) {
				throw new EmailServiceConnectorException("Unable to process request in SendGrid");
			}		
		} catch (IOException e) {
			throw new EmailServiceConnectorException(e);			
		}

	}

	private Email createSendGridEmailAddress(EmailAddress emailAddress) {
		Email sendGridEmail = new Email();
		if (emailAddress.getName() != null) {
			sendGridEmail.setName(emailAddress.getName());
		}
		sendGridEmail.setEmail(emailAddress.getEmail());
		return sendGridEmail;
	}
}
