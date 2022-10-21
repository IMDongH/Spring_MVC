package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value = {}",name,session.getAttribute(name)));

        //session이 기본적으로 제공하는 정보
        log.info("sessionId = {}",session.getId());
        log.info("getMaxInactiveInterval = {}",session.getMaxInactiveInterval()); //
        log.info("getCreationTime = {}", new Date(session.getCreationTime())); //session 생성 시간
        log.info("getLastAccessedTime = {}", new Date(session.getLastAccessedTime())); //session에 사용자가 마지막으로 접근한 시간
        log.info("isNew = {}", session.isNew()); //session 이 새로 생성된 것인지
        return "세션 출력";
    }
}
