package pl.szelemekto.emailservicedemo.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@JsonInclude(value=Include.NON_EMPTY)
@JsonPropertyOrder(value={"from","to","cc","bcc","subject","body"})
@ApiModel(value="Email", description="Email model")
public class EmailMsg {
	
	
	@ApiModelProperty(position=1, required=true, value = "mail sender details")	
	@NotNull
	@Valid
	EmailAddress from;
		
	@ApiModelProperty(position=2, required=true, value = "list of email recepients")
	@NotEmpty
	@NotNull
	@Valid
	public List<EmailAddress> to;
	
	@ApiModelProperty(position=3, value = "list of email cc recepients")
	@Valid
	public List<EmailAddress> cc;
	
	@ApiModelProperty(position=4, value = "list of email bcc recepients")
	@Valid
	public List<EmailAddress> bcc;
	
	@ApiModelProperty(position=5, required=true, value = "email subject", example="this is an eamil")
	@NotNull
	@Length(min=1)
	public String subject;
	
	@ApiModelProperty(position=6, required=true, value = "email subject", example="welcome to email service demo app by Szelemetko")
	@NotNull
	public String body;
	
}
