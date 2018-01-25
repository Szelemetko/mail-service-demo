package pl.szelemekto.emailservicedemo.exception;


public class EmailServiceConnectorException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailServiceConnectorException(Exception e) {
		super(e);
	}
	
	public EmailServiceConnectorException(String message) {
		super(message);
	}
}
