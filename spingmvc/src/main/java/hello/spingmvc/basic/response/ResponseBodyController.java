package hello.spingmvc.basic.response;

import hello.spingmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody 는 모두에 ResponseBody 가 붙는다
//@RestController 가 위 두개를 합친것. api만들때는 보통 이걸 쓴다.
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v2")
    public String responseBodyV3(){
        return "ok";
    }

    @GetMapping("response-body-json-v1")
    public ResponseEntity<HelloData> responseJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("test");
        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)//@ResponseBody 의 경우에는 이게 없으면 상태코드를 보낼 수 없음
    //상태코드를 동적으로 보내야할때는 ResponseEntity 를 사용한다.
    @ResponseBody
    @GetMapping("response-body-json-v2")
    public HelloData responseJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("test");
        return helloData;
    }


}
