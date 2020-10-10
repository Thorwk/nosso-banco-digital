package com.zup.bootcamp.nossobancodigital.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { })
@NotBlank(message = "Cep é obrigatório!")
@Size(max = 10, message = "Cep inválido!")
@Pattern(message = "Cep inválido!", regexp = "^[0-9]{5}-[0-9]{3}$")
public @interface Cep {

    String message() default "Cep inválido!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
