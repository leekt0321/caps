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
        // 여기에 인증 로직을 넣어주세요. Spring Security 또는 사용자 정의 로직을 사용할 수 있습니다.
        boolean isAuthenticated = entityService.authenticateUser(member.getId(), member.getPw());

        if(isAuthenticated) {// 인증이 성공하면 쿠키를 생성합니다.
            Cookie cookie = new Cookie("userCookie", "userIdentifier"); // 여기서 사용자 식별자를 설정할 수 있습니다.
            cookie.setMaxAge(3600); // 쿠키 만료 시간:1시간 (초 단위)

            response.addCookie(cookie);
            // 로그인 성공 후 안전한 페이지로 리디렉션합니다.
            return "redirect:/main"; // 여기서 보안된 페이지의 URL로 변경해주세요.
        }
        else{
            attributes.addFlashAttribute("loginError", "잘못 입력했습니다. 다시 입력해주세요.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(name = "userCookie", required = false) Cookie cookie, HttpServletResponse response) {
        if (cookie != null) {
            cookie.setMaxAge(0); // 쿠키를 즉시 만료시킵니다.
            response.addCookie(cookie);
        }

        return "redirect:/login"; // 여기서 로그인 페이지의 URL로 변경해주세요.
    }

}

