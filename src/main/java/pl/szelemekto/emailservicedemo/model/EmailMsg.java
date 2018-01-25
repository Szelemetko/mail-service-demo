package pl.szelemekto.emailservicedemo.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value=Include.NON_EMPTY)
public class EmailMsg {
	
	@NotNull
	@Valid
	EmailAddress from;
		
	@NotEmpty
	@NotNull
	@Valid
	public List<EmailAddress> to;
	
	@Valid
	public List<EmailAddress> cc;
	
	@Valid
	public List<EmailAddress> bcc;
	
	@NotNull
	@Length(min=1)
	public String subject;
	
	@NotNull
	public String body;
	
}
