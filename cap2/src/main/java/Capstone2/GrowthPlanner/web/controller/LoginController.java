package Capstone2.GrowthPlanner.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import Capstone2.GrowthPlanner.user.service.EntityService;

@Controller
public class LoginController {

    @Autowired
    private EntityService entityService;

    @PostMapping("/login")
    public String login(Member member, HttpServletResponse response, RedirectAttributes attributes) {
        // 사용자 인증 (자격 증명 유효성 검사 등)
        boolean isAuthenticated = entityService.authenticateUser(member.getId(), member.getPw());

        if(isAuthenticated) {
            Cookie cookie = new Cookie("userCookie", member.getId()); //쿠키 이름
            cookie.setMaxAge(3600); // 쿠키 만료 시간:1시간 (초 단위임)

            response.addCookie(cookie);

            return "redirect:/main";
        }
        else{
            attributes.addFlashAttribute("loginError", "잘못 입력했습니다. 다시 입력해주세요.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(name = "userCookie", required = false) Cookie cookie, HttpServletResponse response) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "redirect:/login";
    }

}

