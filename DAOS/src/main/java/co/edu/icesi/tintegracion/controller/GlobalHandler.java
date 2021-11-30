package co.edu.icesi.tintegracion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalHandler {

    @GetMapping("access-denied")
    public String accessDenied() {
        return "access-denied/error";
    }

    @GetMapping("/index.html")
    public String index() {
        return "/index";
    }
}
