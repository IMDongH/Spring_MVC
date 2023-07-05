package hello.proxy.app.v2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping //@Controller or @RequestMapping 이 있어야 스프링 컨트롤러로 인식함
@ResponseBody
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceV2;

    public OrderControllerV2(OrderServiceV2 orderServiceV2) {
        this.orderServiceV2 = orderServiceV2;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderServiceV2.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
