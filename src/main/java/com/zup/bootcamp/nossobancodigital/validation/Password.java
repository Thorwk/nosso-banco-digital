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
@Pattern(message = "Sua senha deve ter um comprimento mínimo de 8 caracteres e pelo menos 1 de cada um dos seguintes: " +
        "maiúsculas, minúsculas, números e símbolos",
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
public @interface Password {

    String message() default "Senha inválida!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
