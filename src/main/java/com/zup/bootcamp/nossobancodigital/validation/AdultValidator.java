package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, String> {

    private static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(String nascimento, ConstraintValidatorContext constraintValidatorContext) {
        return nascimento != null && LocalDate.now().minusYears(ADULT_AGE).isAfter(LocalDate.parse(nascimento));
    }
}
