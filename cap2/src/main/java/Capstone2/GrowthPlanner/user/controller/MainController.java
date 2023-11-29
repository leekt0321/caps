// MainController.java
package Capstone2.GrowthPlanner.user.controller;

import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Game;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import Capstone2.GrowthPlanner.user.service.EntityService;
import Capstone2.GrowthPlanner.user.service.GameService;
import Capstone2.GrowthPlanner.user.repository.GameRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import java.util.List;
import org.aspectj.lang.reflect.MemberSignature;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import java.security.Principal;

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
    @Autowired
    private GameService gameService;

    @GetMapping("/menu3") // userId는 실제 사용자 ID로 대체되어야 합니다.
    public String getMenu3Page(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }

        if (userId != null) {
            Member loggedInMember = memberRepository.findById(userId);
            if (loggedInMember != null) {
                model.addAttribute("member", loggedInMember);

                Game game = gameService.getGameInfo(loggedInMember.getSeq());
                model.addAttribute("game", game);

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

    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/checkAttendance")
    public String chaeckAttendance(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }

        if (userId != null) {
            Member loggedInMember = memberRepository.findById(userId);
            if (loggedInMember != null) {
                Game game = loggedInMember.getGame();
                if (game != null) {
                    if (!game.isAtt_check()) {
                        Long currentPatient = game.getPatient();
                        game.setPatient(currentPatient + 1);
                        game.setAtt_check(true);

                        gameRepository.save(game);
                    }
                    else {
                        model.addAttribute("message", "이미 출석 체크를 하였습니다.");
                        return "content/menu5";
                    }

                }
            }

        }
        return "redirect:/menu5";
    }

    //출석체크 상태 초기화 스케줄러
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul")
    public void resetAttendanceStatus() {
        List<Game> games=gameRepository.findAll();
        for (Game game : games) {
            game.setAtt_check(false);
        }
        gameRepository.saveAll(games);

    }

}

