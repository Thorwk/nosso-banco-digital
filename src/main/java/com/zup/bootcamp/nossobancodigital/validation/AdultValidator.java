package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdultValidator implements ConstraintValidator<Adult, String> {

    private static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(String nascimento, ConstraintValidatorContext constraintValidatorContext) {
        try{
            LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){
            return true;
        }
        return LocalDate.now().minusYears(ADULT_AGE).isAfter(LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
