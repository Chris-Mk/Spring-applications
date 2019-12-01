package com.mkolongo.exodia.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginUserBindingModel {

    @NotBlank(message = "Username required!")
    private String username;

    @NotBlank(message = "Password required!")
    private String password;
}
