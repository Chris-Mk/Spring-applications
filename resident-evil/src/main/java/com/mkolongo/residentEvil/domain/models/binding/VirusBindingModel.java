package com.mkolongo.residentEvil.domain.models.binding;

import com.mkolongo.residentEvil.domain.entities.Creator;
import com.mkolongo.residentEvil.domain.entities.Magnitude;
import com.mkolongo.residentEvil.domain.entities.Mutation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class VirusBindingModel {

    @NotBlank(message = "Invalid name!")
    @Length(min = 3, max = 10, message = "Name must be between 3 and 10 characters long!")
    private String name;

    @NotBlank(message = "Invalid description!")
    @Length(min = 5, max = 100, message = "Description must be between 5 and 100 characters long!")
    private String description;

    @Length(max = 50, message = "Side Effects must be less than 50 characters long!")
    private String sideEffects;

    private Creator creator;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull(message = "Mutation required!")
    private Mutation mutation;

    @Range(min = 0, max = 100, message = "Turnover Rate must be between 0 and 100!")
    private byte turnoverRate;

    @Range(min = 1, max = 12, message = "Hours Until Turn must be between 1 and 12!")
    private byte hoursUntilTurnover;

    @NotNull(message = "Magnitude required!")
    private Magnitude magnitude;

    @Past(message = "Release Date must be before today!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private Set<String> affectedCapitals;
}
