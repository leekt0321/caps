package Capstone2.GrowthPlanner.user.controller;
//보통 controller패키지는 입력(input)으로 사용

// controller로 요청이오면 Service로 전달하고 Service는 Repository로 전송해 Repository가 DB와 통신하도록 설계할거임.

import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {

    @GetMapping("/")
    public String mainpage() {
        return "mainP";
    }
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {         //입력 폼을 보여줌
        model.addAttribute("member", new Member());
        return "signup"; // signup 템플릿
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute Member member) {    // POST요청을 처리, 사용자가 입력한 회원정보를 받아 DB에 저장

        memberRepository.save(member); //회원번호 저장
        return "redirect:/signup-success?name=" + member.getName() + // 회원 정보를 signup-success로 전달
                "&age=" + member.getAge();
    }
    @GetMapping("/signup-success")
    public String showSignupSuccess(@RequestParam String name,      // 입력한 회원 정보를 받아 모델에 추가해 signup-success.html로 표시.
                                    @RequestParam Long age,
                                    Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "signup-success";// signup-success.html 템플릿 파일
    }



}



