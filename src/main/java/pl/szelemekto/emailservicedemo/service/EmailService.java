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
	
	public void send(EmailMsg email) {
		for(EmailProviderConnector connector : connectors) {
			try {
				System.out.println(connector);
				connector.send(email);
//				return;
			} catch(EmailServiceConnectorException e) {
				continue;
			}
		}
	}
	
	@Autowired
	public void setConnectors(List<EmailProviderConnector> connectors) {
		this.connectors = connectors;
	}

}
