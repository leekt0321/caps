// MainController.java
package Capstone2.GrowthPlanner.user.controller;

import javax.servlet.http.Cookie;

import Capstone2.GrowthPlanner.user.Diary.Diary;
import Capstone2.GrowthPlanner.user.Diary.DiaryService;
import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/main")
    public String mainPage() {
        return "frame";
    }

    @GetMapping("/menu1")
    public String menu1(Model model, @CookieValue(name = "userCookie", required = false) String userId) {
        if (userId == null) {
            // 쿠키가 없거나 사용자 ID가 없을 경우 로그인 페이지로 리디렉션
            return "redirect:/login";
        }

        // 쿠키에서 가져온 userId를 사용하여 사용자의 다이어리 목록을 가져옵니다.
        List<Diary> userDiaries = diaryService.getUserDiaries(userId);

        model.addAttribute("userDiaries", userDiaries);
        return "content/menu1";
    }

    @Autowired
    private EntityService entityService;

    @PostMapping("/menu1/saveDiary")
    public String saveDiary(@RequestParam String diaryContent,
                            @CookieValue(name = "userCookie", required = false) String userId) {
        if (userId == null) {
            return "redirect:/menu5";
        }

        Member user = memberRepository.findById(userId);
        if (user == null) {
            return "redirect:/login";
        }

        try {

            // 조회된 Member 객체와 함께 DiaryService의 saveDiary 메서드를 호출합니다.
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
