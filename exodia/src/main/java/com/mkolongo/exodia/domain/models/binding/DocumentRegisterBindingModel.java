package com.mkolongo.exodia.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DocumentRegisterBindingModel {

    @NotBlank(message = "Title field!")
    private String title;

    @NotBlank(message = "Content field!")
    private String content;
}
