package com.mkolongo.residentEvil.service;

import com.mkolongo.residentEvil.domain.entities.Capital;
import com.mkolongo.residentEvil.domain.entities.Virus;
import com.mkolongo.residentEvil.domain.models.service.VirusServiceModel;
import com.mkolongo.residentEvil.domain.models.view.VirusViewModel;
import com.mkolongo.residentEvil.repository.CapitalRepository;
import com.mkolongo.residentEvil.repository.VirusRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class VirusServiceImpl implements VirusService {

    private final CapitalRepository capitalRepository;
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(CapitalRepository capitalRepository,
                            VirusRepository virusRepository,
                            ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void spreadVirus(VirusServiceModel virusServiceModel) {
        final Set<Capital> affectedCapitals =
                capitalRepository.findCapitalsByNameIn(virusServiceModel.getAffectedCapitals());

        final Virus virus = modelMapper.map(virusServiceModel, Virus.class);
        virus.setCapitals(affectedCapitals);

        virusRepository.saveAndFlush(virus);
    }

    @Override
    public Set<VirusViewModel> getAllViruses() {
        if (virusRepository.count() != 0) {
            return modelMapper.map(virusRepository.findAll(),
                    new TypeToken<Set<VirusViewModel>>() {}.getType());
        }
        return new HashSet<>();
    }

    @Override
    public void deleteVirusById(Long id) {
        virusRepository.deleteById(id);
    }

    @Override
    public VirusServiceModel getVirusById(Long id) {
        final Optional<Virus> virus = virusRepository.findById(id);
        return virus.map(value -> modelMapper.map(value, VirusServiceModel.class)).orElse(null);
    }

    @Override
    @Transactional
    public void editVirus(VirusServiceModel virusServiceModel) {
        final Set<Capital> affectedCapitals =
                capitalRepository.findCapitalsByNameIn(virusServiceModel.getAffectedCapitals());

        final Virus editedVirus = modelMapper.map(virusServiceModel, Virus.class);
        editedVirus.setCapitals(affectedCapitals);

        virusRepository.findById(editedVirus.getId())
                .ifPresent(virus -> {
                    editedVirus.setReleaseDate(virus.getReleaseDate());
                });

        virusRepository.saveAndFlush(editedVirus);
    }
}
