package hello.spingmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(MappingController.class);

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
}
