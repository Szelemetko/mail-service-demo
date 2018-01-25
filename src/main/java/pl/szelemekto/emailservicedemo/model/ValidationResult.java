package pl.szelemekto.emailservicedemo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationResult {

	List<ValidationError> errors;
	
	public ValidationResult() {
		this.errors = new ArrayList<>();
	}
	
	public void addError(String field, String message) {
		ValidationError error = new ValidationError(field, message);
		errors.add(error);
	}
}
