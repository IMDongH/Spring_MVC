package hello.spingmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(MappingController.class);

    //method 지정 안하면 어떤 method 로 호출 해도 실행된다.
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("hello basic");

        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("hello basic");

        return "ok";
    }
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId = {}",data);

        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/oders/{orderId}")
    public String mappingPath(@PathVariable String userId,@PathVariable String orderId){
        log.info("mappingPath userId = {}, orderId = {}",userId,orderId);

        return "ok";
    }

    //json 형식이어야만 요청이 된다.
    //consume 은 요청 헤더의 컨텐트 타입
    //produce는 요청 헤더의 어셉트 기반으로 매핑
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume(){
        log.info("mapping consume");

        return "ok";
    }
}
