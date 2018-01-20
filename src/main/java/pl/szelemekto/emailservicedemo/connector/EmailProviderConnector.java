package pl.szelemekto.emailservicedemo.connector;

import java.io.IOException;

import pl.szelemekto.emailservicedemo.model.EmailMsg;

public interface EmailProviderConnector {

	void send(EmailMsg email) throws IOException;
}
