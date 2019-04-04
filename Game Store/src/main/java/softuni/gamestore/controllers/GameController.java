package softuni.gamestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.gamestore.entity.Game;
import softuni.gamestore.gameBindingModel.GameBindingModel;
import softuni.gamestore.repository.GameRepository;

import java.util.List;


@Controller
public class GameController {

    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        List<Game> games = this.gameRepository.findAll();

        modelAndView.addObject("games", games);
        modelAndView.addObject("view", "game/index");
        modelAndView.setViewName("base-layout");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView) {
        modelAndView.addObject("view", "game/create");
        modelAndView.setViewName("base-layout");
        return modelAndView;

    }

    @PostMapping("/create")
    public String create(Game game) {
        this.gameRepository.saveAndFlush(game);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable int id) {
        Game game = this.gameRepository.findById(id).get();

        modelAndView.addObject("game", game);
        modelAndView.addObject("view", "game/delete");
        modelAndView.setViewName("base-layout");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable int id) {
        Game game = this.gameRepository.findById(id).get();

        modelAndView.addObject("game", game);
        modelAndView.addObject("view", "game/edit");
        modelAndView.setViewName("base-layout");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String delete(@PathVariable int id, GameBindingModel gameBindingModel) {
        Game game = this.gameRepository.findById(id).get();

        game.setName(gameBindingModel.getName());
        game.setDlc(gameBindingModel.getDlc());
        game.setHonorarium(gameBindingModel.getHonorarium());
        game.setGenre(gameBindingModel.getGenre());
        this.gameRepository.saveAndFlush(game);
        return "redirect:/";
    }


    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable int id) {
        this.gameRepository.deleteById(id);
        return "redirect:/";

    }
}
