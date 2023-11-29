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

import java.util.ArrayList;
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
    public String menu4(Model model, HttpServletRequest request,@RequestParam(value="mission1", required = false) String mission1,
                        @RequestParam(value="mission2", required = false) String mission2,
                        @RequestParam(value="mission3", required = false) String mission3,
                        @RequestParam(value="mission4", required = false) String mission4) {
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
                Game game=loggedInMember.getGame();
                if(game!=null){

                    Long level=game.getLevel();
                    Long count=game.getCount();
                    if(count<7&&count>=0) game.setLevel(1L);
                    else if(count<14&&count>=7) game.setLevel(2L);
                    else if(count<21&&count>=14) game.setLevel(3L);
                    else if(count<28&&count>=21) game.setLevel(4L);
                    else if(count>=28) game.setLevel(5L);
                    else model.addAttribute("error","무언가 잘 못되었습니다.^^");

                    Long patient=game.getPatient();
                    if(level==1&&count>=0&&count<7){
                        model.addAttribute("quest1","야식먹지않기");
                        model.addAttribute("quest2","공원 걷기");
                        model.addAttribute("quest3","밥먹고 앉아있기");
                        model.addAttribute("quest4","일기 쓰기");
                        boolean mission1Checked = mission1 != null;
                        boolean mission2Checked = mission2 != null;
                        boolean mission3Checked = mission3 != null;
                        boolean mission4Checked = mission4 != null;
                        boolean allMissionsCompleted=mission1Checked && mission2Checked && mission3Checked && mission4Checked;
                        if(allMissionsCompleted) {
                            game.setPatient(patient + 1);
                            gameRepository.save(game);


                        }
                    }
                    else if(level==2&&count>=7&&count<14){
                        model.addAttribute("quest1","아침 챙겨먹기");
                        model.addAttribute("quest2","자전거 타기");
                        model.addAttribute("quest3","늦게 자지않기");
                        model.addAttribute("quest4","동기부여 영상 보기");
                        boolean mission1Checked = mission1 != null;
                        boolean mission2Checked = mission2 != null;
                        boolean mission3Checked = mission3 != null;
                        boolean mission4Checked = mission4 != null;
                        boolean allMissionsCompleted=mission1Checked && mission2Checked && mission3Checked && mission4Checked;
                        if(allMissionsCompleted) {
                            game.setPatient(patient + 2);
                            gameRepository.save(game);


                        }
                    }
                    else if(level==3&&count>=14&&count<21){
                        model.addAttribute("quest1","간식 및 음료 먹지않기");
                        model.addAttribute("quest2","3Km 걷기");
                        model.addAttribute("quest3","금주");
                        model.addAttribute("quest4","썼던 일기 다시보기");
                        boolean mission1Checked = mission1 != null;
                        boolean mission2Checked = mission2 != null;
                        boolean mission3Checked = mission3 != null;
                        boolean mission4Checked = mission4 != null;
                        boolean allMissionsCompleted=mission1Checked && mission2Checked && mission3Checked && mission4Checked;
                        if(allMissionsCompleted) {
                            game.setPatient(patient + 3);
                            gameRepository.save(game);
                        }
                    }
                    else if(level==4&&count>=21&&count<28){
                        model.addAttribute("quest1","저녁 양 줄이기");
                        model.addAttribute("quest2","3Km 뛰기");
                        model.addAttribute("quest3","엘리베이터 대신 계닫 이용 하기");
                        model.addAttribute("quest4","목표 중간 점검!");
                        boolean mission1Checked = mission1 != null;
                        boolean mission2Checked = mission2 != null;
                        boolean mission3Checked = mission3 != null;
                        boolean mission4Checked = mission4 != null;
                        boolean allMissionsCompleted=mission1Checked && mission2Checked && mission3Checked && mission4Checked;
                        if(allMissionsCompleted) {
                            game.setPatient(patient + 4);
                            gameRepository.save(game);
                        }
                    }
                    else if(level==5&&count>=28){
                        model.addAttribute("quest1","탄수화물 줄이기");
                        model.addAttribute("quest2","3Km + 근력운동");
                        model.addAttribute("quest3","배달음식 먹지 않기");
                        model.addAttribute("quest4","출석!! 목표까지 유지하세요. 화이팅!");
                        boolean mission1Checked = mission1 != null;
                        boolean mission2Checked = mission2 != null;
                        boolean mission3Checked = mission3 != null;
                        boolean mission4Checked = mission4 != null;
                        boolean allMissionsCompleted=mission1Checked && mission2Checked && mission3Checked && mission4Checked;
                        if(allMissionsCompleted) {
                            game.setPatient(patient + 5);
                            gameRepository.save(game);
                        }
                    }
                    else{
                        model.addAttribute("error","무언가 잘 못되었습니다.^^");
                    }

                }



                return "content/menu4";
            }

        }
        return "login";
    }
    @PostMapping("/checkAttendanceM")
    public String chaeckAttendanceM(HttpServletRequest request, Model model) {
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
                    return "content/menu1";

                }
            }

        }
        return "login";
    }
    @PostMapping("/resetCount")
    public String resetCount(Model model, HttpServletRequest request) {
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
                    game.setCount(0L);
                    gameRepository.save(game);
                    return "redirect:/menu4";
                }

            }
        }
        return "login";
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
                        model.addAttribute("message","출석 체크가 완료되었습니다.");
                        game.setCount(game.getCount()+1);
                        return "content/menu5";
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

