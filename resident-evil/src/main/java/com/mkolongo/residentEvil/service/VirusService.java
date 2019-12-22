package com.mkolongo.residentEvil.service;

import com.mkolongo.residentEvil.domain.models.service.VirusServiceModel;
import com.mkolongo.residentEvil.domain.models.view.VirusViewModel;

import java.util.Set;

public interface VirusService {

    void spreadVirus(VirusServiceModel virusServiceModel);

    Set<VirusViewModel> getAllViruses();

    void deleteVirusById(Long id);

    VirusServiceModel getVirusById(Long id);

    void editVirus(VirusServiceModel virusServiceModel);
}
