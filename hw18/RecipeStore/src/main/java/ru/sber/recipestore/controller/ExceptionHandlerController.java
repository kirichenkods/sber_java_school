package ru.sber.recipestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());

        return "error";
    }
}
