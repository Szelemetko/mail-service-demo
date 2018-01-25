package pl.szelemekto.emailservicedemo.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content=Include.NON_EMPTY)
public class EmailAddress {
	
	private String name;
	
	@Email
	@NotNull
	private String email;
}
