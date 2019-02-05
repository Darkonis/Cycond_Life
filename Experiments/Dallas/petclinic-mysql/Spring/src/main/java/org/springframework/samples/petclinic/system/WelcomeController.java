package org.springframework.samples.petclinic.system;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome to a boring home screen created by DALLAS EVERS";
    }
    
    @GetMapping("/home")
    public String home() {
        return "There is no place like home. Made BY D@!!@5";
    }
}
