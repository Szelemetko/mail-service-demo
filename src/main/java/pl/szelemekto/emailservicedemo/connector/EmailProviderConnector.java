package pl.szelemekto.emailservicedemo.connector;

import pl.szelemekto.emailservicedemo.exception.EmailServiceConnectorException;
import pl.szelemekto.emailservicedemo.model.EmailMsg;

public interface EmailProviderConnector {

	void send(EmailMsg email) throws EmailServiceConnectorException;
}
