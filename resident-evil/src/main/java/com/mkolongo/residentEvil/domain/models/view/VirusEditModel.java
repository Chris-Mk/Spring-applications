package com.mkolongo.residentEvil.domain.models.view;

import com.mkolongo.residentEvil.domain.entities.Magnitude;
import com.mkolongo.residentEvil.domain.entities.Mutation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class VirusEditModel {

    private Long id;
    private String name;
    private String description;
    private String sideEffects;
    private String creator;
    private boolean isDeadly;
    private boolean isCurable;
    private Mutation mutation;
    private byte turnoverRate;
    private byte hoursUntilTurnover;
    private Magnitude magnitude;
    private LocalDate releaseDate;
    private Set<String> affectedCapitals;
}
