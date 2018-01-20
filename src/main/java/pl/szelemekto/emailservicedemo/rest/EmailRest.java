package pl.szelemekto.emailservicedemo.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.szelemekto.emailservicedemo.model.EmailMsg;
import pl.szelemekto.emailservicedemo.service.EmailService;



@RestController
@RequestMapping("/email")
public class EmailRest {
	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public EmailMsg send(@RequestBody @Valid EmailMsg email, BindingResult bindingResult) {
		emailService.send(email);
		return email;
	}
}
