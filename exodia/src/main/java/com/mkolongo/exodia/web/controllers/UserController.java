package com.mkolongo.exodia.web.controllers;

import com.mkolongo.exodia.domain.models.binding.LoginUserBindingModel;
import com.mkolongo.exodia.domain.models.binding.RegisterUserBindingModel;
import com.mkolongo.exodia.domain.models.service.RegisterUserServiceModel;
import com.mkolongo.exodia.domain.models.view.DocumentViewModel;
import com.mkolongo.exodia.services.DocumentService;
import com.mkolongo.exodia.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private static final String LOGIN_PAGE = "login";
    private static final String REGISTER_PAGE = "register";
    private static final String HOME_PAGE = "home";

    private final DocumentService documentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(DocumentService documentService, UserService userService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN_PAGE);
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginUserBindingModel loginUserBindingModel,
                        BindingResult bindingResult,
                        HttpSession httpSession) {
        userService.findUserByUsername(loginUserBindingModel.getUsername());
        if (bindingResult.hasErrors() || userService.findUserByUsername(loginUserBindingModel.getUsername()).isEmpty()) {
            return "redirect:/login";
        }

        httpSession.setAttribute("username", loginUserBindingModel.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTER_PAGE);
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserBindingModel bindingModel, BindingResult bindingResult) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()
                || !(bindingModel.getPassword().equals(bindingModel.getConfirmPassword()))
                || userService.findUserByUsername(bindingModel.getUsername()).isPresent()) {
            return "redirect:/register";
        }

        userService.registerUser(this.modelMapper.map(bindingModel, RegisterUserServiceModel.class));
        return "redirect:/login";
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession, ModelAndView modelAndView) {
        final String username = (String) httpSession.getAttribute("username");
        final Set<DocumentViewModel> documents = documentService.getAllDocumentsByUsername(username);

        modelAndView.addObject("documents", documents);
        modelAndView.setViewName(HOME_PAGE);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
