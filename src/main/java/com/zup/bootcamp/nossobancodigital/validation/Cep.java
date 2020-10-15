package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { })
@NotBlank(message = "Cep é obrigatório!")
@Pattern(message = "Cep inválido!", regexp = "^[0-9]{5}-[0-9]{3}$")
public @interface Cep {

    String message() default "Cep inválido!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
