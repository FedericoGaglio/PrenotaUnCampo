package it.uniroma3.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.model.Presidente;

public class PresidenteValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Presidente.class.equals(aClass);
	}	
}