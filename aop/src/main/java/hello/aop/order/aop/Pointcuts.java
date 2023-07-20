package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut
    public void allOrder(){}

    //클래스 이름 패턴이 *Service인 것
    @Pointcut("execution(* *..*Service.*(..))") //pointcut
    public void allService(){}

    @Pointcut("allOrder() && allService()") //pointcut
    public void orderAndService(){}
}
