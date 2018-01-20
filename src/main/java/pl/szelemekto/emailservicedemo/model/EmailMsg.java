package pl.szelemekto.emailservicedemo.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class EmailMsg {
	
	@Email
	@NotNull
	public String from;
	
	@Email
	@NotNull
	public String to;
	
	@NotNull
	@Length(min=1)
	public String subject;
	
	@NotNull
	public String body;
}
