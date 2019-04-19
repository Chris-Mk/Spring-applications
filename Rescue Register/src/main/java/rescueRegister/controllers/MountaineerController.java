package rescueRegister.controllers;

import rescueRegister.bindingModels.MountaineerBindingModel;
import rescueRegister.entities.Mountaineer;
import rescueRegister.repositories.MountaineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MountaineerController {
    private final MountaineerRepository mountaineerRepository;

    @Autowired
    public MountaineerController(MountaineerRepository mountaineerRepository) {
        this.mountaineerRepository = mountaineerRepository;
    }


    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "mountaineer/Index");
        modelAndView.addObject("mountaineers", this.mountaineerRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "mountaineer/Create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Mountaineer mountaineer) {
        this.mountaineerRepository.saveAndFlush(mountaineer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "mountaineer/Edit");
        modelAndView.addObject("mountaineer", this.mountaineerRepository.getOne(id));
        return modelAndView;
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable(value = "id") Integer id, MountaineerBindingModel mountaineerBindingModel) {
        Mountaineer mountaineer = this.mountaineerRepository.getOne(id);

        mountaineer.setName(mountaineerBindingModel.getName());
        mountaineer.setAge(mountaineerBindingModel.getAge());
        mountaineer.setGender(mountaineerBindingModel.getGender());
        mountaineer.setLastSeenDate(mountaineerBindingModel.getLastSeenDate());
        this.mountaineerRepository.saveAndFlush(mountaineer);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView remove(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "mountaineer/Remove");
        modelAndView.addObject("mountaineer", this.mountaineerRepository.getOne(id));
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String remove(Mountaineer mountaineer) {
        this.mountaineerRepository.delete(mountaineer);
        return "redirect:/";
    }
}
