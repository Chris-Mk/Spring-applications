package com.mkolongo.residentEvil.controllers;

import com.mkolongo.residentEvil.domain.entities.Creator;
import com.mkolongo.residentEvil.domain.entities.Magnitude;
import com.mkolongo.residentEvil.domain.entities.Mutation;
import com.mkolongo.residentEvil.domain.models.binding.VirusBindingModel;
import com.mkolongo.residentEvil.domain.models.service.VirusServiceModel;
import com.mkolongo.residentEvil.domain.models.view.VirusEditModel;
import com.mkolongo.residentEvil.service.CapitalService;
import com.mkolongo.residentEvil.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class VirusController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/viruses/add")
    public ModelAndView addVirus(ModelAndView modelAndView) {
        modelAndView.addObject(new VirusBindingModel());

        modelAndView.addObject("capitals", capitalService.getAllCapitalNames());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("creators", Creator.values());
        modelAndView.addObject("mutations", Mutation.values());

        modelAndView.setViewName("add");

        return modelAndView;
    }

    @PostMapping("/viruses/add")
    public String addVirus(@Valid @ModelAttribute VirusBindingModel virusBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }

        final VirusServiceModel virusServiceModel = modelMapper.map(virusBindingModel, VirusServiceModel.class);
        virusService.spreadVirus(virusServiceModel);
        return "redirect:/";
    }

    @GetMapping("/viruses")
    public ModelAndView showViruses(ModelAndView modelAndView) {
        modelAndView.addObject("viruses", virusService.getAllViruses());
        modelAndView.setViewName("all-viruses");
        return modelAndView;
    }

    @GetMapping("/delete/viruses/{id}")
    public String delete(@PathVariable Long id) {
        virusService.deleteVirusById(id);
        return "redirect:/viruses";
    }

    @GetMapping("/edit/viruses/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView modelAndView) {
        final VirusServiceModel serviceModel = virusService.getVirusById(id);
        final VirusEditModel viewModel = modelMapper.map(serviceModel, VirusEditModel.class);

        modelAndView.addObject("capitals", capitalService.getAllCapitalNames());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("creators", Creator.values());
        modelAndView.addObject("mutations", Mutation.values());
        modelAndView.addObject("virus", viewModel);
        modelAndView.setViewName("edit-virus");

        return modelAndView;
    }

    @PostMapping("/edit/viruses/{id}")
    public String edit(@Valid @ModelAttribute VirusEditModel virusEditModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit/viruses";
        }

        final VirusServiceModel serviceModel = modelMapper.map(virusEditModel, VirusServiceModel.class);
        virusService.editVirus(serviceModel);

        return "redirect:/viruses";
    }
}
