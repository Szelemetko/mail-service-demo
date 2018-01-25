package pl.szelemekto.emailservicedemo.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.szelemekto.emailservicedemo.model.EmailMsg;
import pl.szelemekto.emailservicedemo.model.ResponseMessage;
import pl.szelemekto.emailservicedemo.model.ValidationResult;
import pl.szelemekto.emailservicedemo.service.EmailService;

@Api

@RestController
@RequestMapping("/email")
public class EmailRest {
	
	@Autowired
	private EmailService emailService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/send")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="OK"),
	@ApiResponse(code=500, message="Service temporarily unavailable")
	})
	public ResponseEntity<?> send(@RequestBody @Valid EmailMsg email, BindingResult bindingResult) {
		log.info("Received a request:" + email);
		if (bindingResult.hasErrors()) {
			ValidationResult validationResult = new ValidationResult();
			for (FieldError error : bindingResult.getFieldErrors()) {
				validationResult.addError(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(validationResult);
		}

		int status = emailService.send(email);
		if (status == 200) {
			return ResponseEntity.ok().body(new ResponseMessage(true, "OK"));
		} else {
			return ResponseEntity.status(500).body(new ResponseMessage(false, "Service temporarily unavailable"));
		}
	}
}
