package Capstone2.GrowthPlanner.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "frame";
    }

    @GetMapping("/menu1")
    public String menu1() {
        return "content/menu1";
    }

    @GetMapping("/menu2")
    public String menu2() {
        return "content/menu2";
    }

    @GetMapping("/menu3")
    public String menu3() {
        return "content/menu3";
    }

    @GetMapping("/menu4")
    public String menu4() {
        return "content/menu4";
    }

    @GetMapping("/menu5")
    public String menu5() {
        return "content/menu5";
    }
}