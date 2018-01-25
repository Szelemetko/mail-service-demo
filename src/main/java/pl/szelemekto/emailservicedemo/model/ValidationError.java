package pl.szelemekto.emailservicedemo.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonRootName("error")
public class ValidationError {

	private String field;
	
	private String message;

}
		
