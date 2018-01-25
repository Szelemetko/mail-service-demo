package pl.szelemekto.emailservicedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.szelemekto.emailservicedemo.connector.EmailProviderConnector;
import pl.szelemekto.emailservicedemo.exception.EmailServiceConnectorException;
import pl.szelemekto.emailservicedemo.model.EmailMsg;

@Service
public class EmailService {
	
	private List<EmailProviderConnector> connectors;
	
	public int send(EmailMsg email) {
		for(EmailProviderConnector connector : connectors) {
			try {
				connector.send(email);
				return 200;
			} catch(EmailServiceConnectorException e) {
				continue;
			}
		}
		return 500;
	}
	
	@Autowired
	public void setConnectors(List<EmailProviderConnector> connectors) {
		this.connectors = connectors;
	}

}
