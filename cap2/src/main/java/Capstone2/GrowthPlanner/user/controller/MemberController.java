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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MemberController {

    @GetMapping("/")
    public String mainpage() {
        return "mainP";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    // 쿠키 확인 후 조건에 맞게 페이지 이동
                    return "redirect:/main";
                }
            }
        }
        return "login";
    }
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/membership")
    public String showSignupForm(Model model) {         //입력 폼을 보여줌
        model.addAttribute("member", new Member());
        return "membership"; // membership 템플릿
    }

    @PostMapping("/membership")
    public String processSignup(@ModelAttribute Member member) {    // POST요청을 처리, 사용자가 입력한 회원정보를 받아 DB에 저장

        memberRepository.save(member); //회원번호 저장

        String encodedName = UriComponentsBuilder.fromPath("/signup-success")
                .queryParam("name", member.getName())
                .queryParam("age", member.getAge())
                .buildAndExpand()
                .encode()
                .toUriString();
        return "redirect:"+encodedName;
    }
    @GetMapping("/signup-success")
    public String showSignupSuccess(@RequestParam String name,      // 입력한 회원 정보를 받아 모델에 추가해 signup-success.html로 표시.
                                    @RequestParam Long age,
                                    Model model)
    {
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "signup-success";// signup-success.html 템플릿 파일
    }


}



