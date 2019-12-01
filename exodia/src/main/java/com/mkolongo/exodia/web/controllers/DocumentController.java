package com.mkolongo.exodia.web.controllers;

import com.mkolongo.exodia.domain.entities.Document;
import com.mkolongo.exodia.domain.models.binding.DocumentRegisterBindingModel;
import com.mkolongo.exodia.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class DocumentController {
    private static final String SCHEDULE_PATH = "schedule";

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/schedule")
    public String schedule() {
        return SCHEDULE_PATH;
    }

    @PostMapping("/schedule")
    public String schedule(@Valid @ModelAttribute DocumentRegisterBindingModel bindingModel,
                           BindingResult bindingResult,
                           HttpSession session) {
        if (bindingResult.hasErrors() || documentService.getDocumentByTitle(bindingModel.getTitle()).isPresent()) {
            return "redirect:/schedule";
        }

        final String username = (String) session.getAttribute("username");
        documentService.scheduleDocument(bindingModel, username);
        return "redirect:/home";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam String id, ModelAndView modelAndView) {
        final Document document = documentService.getDocumentById(id);
        modelAndView.addObject("document", document);
        modelAndView.setViewName("details");
        return modelAndView;
    }

    @GetMapping("/print")
    public ModelAndView print(@RequestParam String id, ModelAndView modelAndView) {
        final Document document = documentService.getDocumentById(id);
        modelAndView.addObject(document);
        modelAndView.setViewName("print");
        return modelAndView;
    }

    @PostMapping("/print")
    public String print(@RequestParam String id) {
        documentService.deleteDocumentById(id);
        return "redirect:/home";
    }
}
