package pl.szelemekto.emailservicedemo.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(content=Include.NON_EMPTY)
public class EmailAddress {
	
	@ApiModelProperty(value = "mail sender name", example="Jan Kowalski")
	private String name;
	
	@Email
	@NotNull
	@ApiModelProperty(value = "mail sender email", example="jan.kowalski@example.com")
	private String email;
}
