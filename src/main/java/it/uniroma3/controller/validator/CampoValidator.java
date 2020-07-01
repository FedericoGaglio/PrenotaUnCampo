package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.model.Campo;

@Component
public class CampoValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lunghezza", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "larghezza", "required");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Campo.class.equals(aClass);
	}	

}