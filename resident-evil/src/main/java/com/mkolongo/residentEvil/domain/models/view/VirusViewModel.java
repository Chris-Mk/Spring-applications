package com.mkolongo.residentEvil.domain.models.view;

import com.mkolongo.residentEvil.domain.entities.Magnitude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VirusViewModel {

    private Long id;
    private String name;
    private Magnitude magnitude;
    private String releaseDate;

}
