package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Past;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AdultValidator.class)
@Past(message = "Data de nascimento precisa ser anterior ao dia de hoje!")
public @interface Adult {

    String message() default "{adult}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
