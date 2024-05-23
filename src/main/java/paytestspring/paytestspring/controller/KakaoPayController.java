package paytestspring.paytestspring.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import paytestspring.paytestspring.dto.test;
import paytestspring.paytestspring.service.KakaoPayService;

@Controller
@RequiredArgsConstructor
@Log
public class KakaoPayController {
    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaoPay;

//    @PostMapping("test")
//    @ResponseBody       //json으로 반환하는 것이 기본이다.
//    public ResponseEntity<?> helloApi(HttpServletRequest request, @RequestBody test loginRequest){
////        User user = new User();
////        user.setLoginId(loginRequest.getLoginId());
////        user.setPassword(loginRequest.getPassword());
//        System.out.println(loginRequest.getLoginId());
////        log.info("로그인 요청 정보={}", user);
////
////        User loginResult = userService.login(user);
////
////        if (loginResult == null) {
////            throw new LoginFailureException();
////        }
////
////        // 로그인 성공 처리
////        // HttpSession 사용해서 세션처리
////
////        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
////        HttpSession session = request.getSession(true);
////        // 세션에 로그인 회원 정보 보관
////        session.setAttribute(SessionConst.LOGIN_MEMBER, loginResult);
////
////        log.info("session = {}", session.getAttribute(SessionConst.LOGIN_MEMBER));
////
//        return ResponseEntity.ok().body("로그인 성공");
//    }

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(){
        log.info("kakaoPay post.....................");

        return "redirect:" + kakaoPay.kakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model) {
        log.info("kakaoPay Success get................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
    }
}
