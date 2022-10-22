package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
//    @GetMapping("/")
    public String home() {

        return "home";
    }

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId",required=false) Long memberId, Model model){

        if(memberId==null){
            return "home";
        }

        Member findMember = memberRepository.findById(memberId);

        model.addAttribute("member",findMember);
        return "loginHome";
    }
//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){

        Member member = (Member)sessionManager.getSession(request);

        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model){

        //true 로 두면 로그인하지 않고 처음 들어온 사용자도 session 이 만들어져버린다.
        HttpSession session = request.getSession(false);

        if(session==null){
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model){

        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member member, Model model){

        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}