package pl.szelemekto.emailservicedemo.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import pl.szelemekto.emailservicedemo.model.EmailMsg;
import pl.szelemekto.emailservicedemo.model.ValidationResult;
import pl.szelemekto.emailservicedemo.service.EmailService;

@Api
@RestController
@RequestMapping("/email")
public class EmailRest {
	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<?> send(@RequestBody @Valid EmailMsg email, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			ValidationResult validationResult = new ValidationResult();
			for (FieldError error : bindingResult.getFieldErrors()) {
				validationResult.addError(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(validationResult);
		}
		
		emailService.send(email);
		return ResponseEntity.ok().body(email);
	}
}
