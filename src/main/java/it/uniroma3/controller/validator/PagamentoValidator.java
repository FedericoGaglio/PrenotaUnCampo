package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.model.Pagamento;

@Component
public class PagamentoValidator  implements Validator{

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "importo", "required");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Pagamento.class.equals(aClass);
	}	

}