package Capstone2.GrowthPlanner.user.controller;

import Capstone2.GrowthPlanner.user.Diary.Diary;
import Capstone2.GrowthPlanner.user.Diary.DiaryService;
import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Game;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import Capstone2.GrowthPlanner.user.service.EntityService;


import Capstone2.GrowthPlanner.user.service.EntityService;
import Capstone2.GrowthPlanner.user.service.GameService;
import org.aspectj.lang.reflect.MemberSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private EntityService entityService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("/main")
    public String mainPage() {
        return "frame";
    }

    @GetMapping("/menu1")
    public String menu1(Model model, @CookieValue(name = "userCookie", required = false) String userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        Member user = memberRepository.findById(userId);
        if (user == null) {
            return "redirect:/login";
        }

        List<Diary> userDiaries = diaryService.getUserDiaries(userId);
        model.addAttribute("userDiaries", userDiaries);
        return "content/menu1";
    }

    @PostMapping("/menu1/saveDiary")
    public String saveDiary(@RequestParam String diaryContent, @CookieValue(name = "userCookie", required = false) String userId) {
        if (userId == null) {
            return "redirect:/menu5";
        }

        Member user = memberRepository.findById(userId);
        if (user == null) {
            return "redirect:/login";
        }

        try {
            diaryService.saveDiary(user, diaryContent);
            return "redirect:/menu1";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/menu2")
    public String menu2() {
        return "content/menu2";
    }


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

                Game game=gameService.getGameInfo(loggedInMember.getSeq());
                model.addAttribute("game",game);

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