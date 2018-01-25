package pl.szelemekto.emailservicedemo.connector;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import pl.szelemekto.emailservicedemo.exception.EmailServiceConnectorException;
import pl.szelemekto.emailservicedemo.model.EmailAddress;
import pl.szelemekto.emailservicedemo.model.EmailMsg;

@Component
public class MailgunConnector implements EmailProviderConnector {

	@Value("${mailgun.key}")
	private String apiKey;

	@Value("${mailgun.domain}")
	private String domain;

	

	@Override
	public void send(EmailMsg email) throws EmailServiceConnectorException {
		try {
			Unirest.setTimeouts(5000, 5000);
			HttpRequestWithBody request = 
					Unirest
					.post("https://api.mailgun.net/v3/" + domain + "/messages")
					.basicAuth("api", apiKey)
					.queryString("from", createEmailAddress(email.getFrom()))
					.queryString("to", createEmailAddressList(email.getTo()))
					.queryString("subject", email.getSubject())
					.queryString("text", email.getBody());
			
			request = addEmailAddressListToRequest(request, "cc", email.getCc());
			request = addEmailAddressListToRequest(request, "bcc", email.getBcc());
					
			HttpResponse<JsonNode> response = request.asJson();
			if(response.getStatus() != 200) {
				throw new EmailServiceConnectorException(response.getStatusText());
			}
		} catch (UnirestException e) {
			throw new EmailServiceConnectorException(e.getMessage());
		}
	}
	
	private HttpRequestWithBody addEmailAddressListToRequest(HttpRequestWithBody request, String field, List<EmailAddress> emails) {
		if(emails == null || emails.isEmpty()) {
			return request;
		}
		return request.queryString(field, createEmailAddressList(emails));	
	}
	
	private String createEmailAddressList(List<EmailAddress> emails) {
		StringBuilder sb = new StringBuilder();
		Iterator<EmailAddress> it = emails.iterator();
		EmailAddress email = null;
		
		while(it.hasNext()) {
			email = it.next();
			sb.append(createEmailAddress(email));				
			if(it.hasNext()) {
				sb.append(",");
			}		
		}	
		return sb.toString();			
	}

	private String createEmailAddress(EmailAddress email) {
		StringBuilder sb = new StringBuilder();
		if(email.getName() != null) {
			sb.append(email.getName());
		}
		sb.append("<");
		sb.append(email.getEmail());
		sb.append(">");
		
		return sb.toString();
	}

}
