package com.turkcell.spring_starter.dto;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.EmbeddableInstantiator;

public class RegisterRequest {

    @NotNull
    @Email
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 6, max = 20)
    private String password;

}
