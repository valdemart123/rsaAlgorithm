package com.encodRsaLab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncoderController {
    @GetMapping("/index")
    public String greetingForm(Model model) {
        model.addAttribute("encoderRsa", new EncoderRsa());
        return "index";
    }

    @PostMapping("/index")
    public String greetingSubmit(@ModelAttribute EncoderRsa encoderRsa, Model model) {
        model.addAttribute("encoderRsa", encoderRsa);
        return "result";
    }
}
