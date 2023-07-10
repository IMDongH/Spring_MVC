package hello.proxy.config.v1_Proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); //proxy 역할만 하기 떄문에 null 넣어줌
        this.target = target;
        this.logTrace = logTrace;
    }


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("orderService.request()");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
