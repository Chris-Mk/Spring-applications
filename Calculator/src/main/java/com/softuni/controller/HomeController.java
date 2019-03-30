package com.softuni.controller;

import com.softuni.entity.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("operator", "+");
        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }

    @PostMapping("/")
    public String calculate(@RequestParam Double leftOperand,
                            @RequestParam String operator,
                            @RequestParam Double rightOperand,
                            Model model) {

        boolean isZero = false;
        if (operator.equals("/") && rightOperand == 0) {
            isZero = true;
        }

        Calculator calculator = new Calculator(leftOperand, operator, rightOperand);
        double result = calculator.calculate();

        model.addAttribute("leftOperand", calculator.getLeftOperand());
        model.addAttribute("operator", calculator.getOperator());
        model.addAttribute("rightOperand", calculator.getRightOperand());
        model.addAttribute("result", result);
        model.addAttribute("flag", isZero);

        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }
}
