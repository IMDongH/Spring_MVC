package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Component
//@RequestMapping 이 둘이 @Controller와 같은 역할

//Controller에 @Component가 있음 그리고 annotation 기반 컨트롤러로 인식한다
//@Component 가 있으면 자동으로 컴포넌트 스캔의 대상이 되어서 자동으로 스프링 빈등록
public class SpringMemberFormV1{
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
