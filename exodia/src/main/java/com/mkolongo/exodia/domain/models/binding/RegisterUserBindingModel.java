package com.mkolongo.exodia.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterUserBindingModel {

    @NotBlank(message = "Username required!")
    private String username;

    @NotBlank(message = "Password required!")
    private String password;

    @NotBlank(message = "Confirm password required!")
    private String confirmPassword;

    @NotBlank(message = "Email required!")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Incorrect email, try again!")
    private String email;
}
