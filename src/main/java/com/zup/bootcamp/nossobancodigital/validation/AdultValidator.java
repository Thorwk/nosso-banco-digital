package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdultValidator implements ConstraintValidator<Adult, String> {

    private static final int IDADE_MINIMA = 18;

    @Override
    public boolean isValid(String nascimento, ConstraintValidatorContext constraintValidatorContext) throws DateTimeException {
        try{
            LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){
            throw new DateTimeException("Data inválida!");
        }
        return LocalDate.now().minusYears(IDADE_MINIMA).isAfter(LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
