package hello.spingmvc.basic.request;

import hello.spingmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}", username);
        log.info("age = {}", age);

        response.getWriter().write("ok");
    }

    @ResponseBody //"ok"를 보내준다. 뷰리졸버를 찾지 않고 ResponseController랑 같은 역할
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
            //request.getParameter 랑 같은 역할
    ){
        log.info("username = {}", memberName);
        log.info("age = {}", memberAge);

        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        //String int Integer 과 같은 단순타입이면 @RequestParam 생략 가능
        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true)//username 이 꼭 들어와야함 없으면 오류난다
            String username,
            @RequestParam(required = false)
            Integer age) {//int 는 null이 안들어가지만 Integer 는 객체 형태이기 때문에 null 이 들어갈 수 있다.
        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue가 들어가면 required가 의미가 없어진다.
            //defaultValue는 빈문자의 경우에도 기본 값으로 넣어준다
            @RequestParam(required = true,defaultValue = "guest") String username,
            @RequestParam(required = false,defaultValue = "-1") int age) {
        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap) {
        log.info("username = {}", paramMap.get("username"));
        log.info("age = {}", paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
//    public String modeAttributeV1(@RequestParam String username, @RequestParam int age){
    public String modeAttributeV1(@ModelAttribute HelloData helloData){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
        //@ModelAttribute 가 위 주석 역할을 다 수행해준다....
        log.info("username = {}", helloData.getUsername());
        log.info("age = {}", helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
//    public String modeAttributeV1(@RequestParam String username, @RequestParam int age){
    public String modeAttributeV2(HelloData helloData){//ModelAttribute 생략 가능하다
        //String int Integer 과 같은 단순 타입은 @RequestParam
        //나머지는 @ModelAttribute 로 처리된다  argument resolver Http 와 같은 애들 뺴고
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
        //@ModelAttribute 가 위 주석 역할을 다 수행해준다....
        log.info("username = {}", helloData.getUsername());
        log.info("age = {}", helloData.getAge());

        return "ok";
    }
}
