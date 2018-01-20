package pl.szelemekto.emailservicedemo.connector;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.szelemekto.emailservicedemo.model.EmailMsg;

@Component
public class MailgunConnector implements EmailProviderConnector {

	@Value("${mailgun.key}")
	private String apiKey;

	@Value("${mailgun.domain}")
	private String domain;

	

	@Override
	public void send(EmailMsg email) throws IOException {
		try {
			HttpResponse<JsonNode> request = 
					Unirest
					.post("https://api.mailgun.net/v3/" + domain + "/messages")
					.basicAuth("api", apiKey)
					.queryString("from", email.getFrom())
					.queryString("to", email.getTo())
					.queryString("subject", email.getSubject())
					.queryString("text", email.getBody())
					.asJson();
		} catch (UnirestException e) {
			throw new IOException(e);
		}

		
	}

}
