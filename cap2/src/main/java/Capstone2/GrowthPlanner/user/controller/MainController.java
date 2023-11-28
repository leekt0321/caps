package Capstone2.GrowthPlanner.user.controller;

import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import Capstone2.GrowthPlanner.user.service.EntityService;


import Capstone2.GrowthPlanner.user.service.EntityService;
import org.aspectj.lang.reflect.MemberSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Autowired
    private EntityService entityService;
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/menu3") // userId는 실제 사용자 ID로 대체되어야 합니다.
    public String getMenu3Page(Model model, HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        String userId=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("userCookie")){
                    userId=cookie.getValue();
                    break;
                }
            }
        }

        if (userId!=null){
            Member loggedInMember=memberRepository.findById(userId);
            if(loggedInMember!=null){
                model.addAttribute("member",loggedInMember);
                return "content/menu3";
            }
        }
        return "login";
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