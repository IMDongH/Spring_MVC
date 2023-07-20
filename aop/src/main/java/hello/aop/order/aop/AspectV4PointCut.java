package hello.aop.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV4PointCut {


    @Order(2)
    @Around("hello.aop.order.aop.Pointcuts.allOrder()") //pointcut
    public Object doLog(ProceedingJoinPoint joinPoint)throws Throwable{
        //advice
        log.info("[log] {}",joinPoint.getSignature());
        return joinPoint.proceed();
    }

    //hello.app.order 패키지와 하위 패키지이면서 클래스 이름 패턴이 *Service 인 것
    @Order(1)
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()") //pointcut
    public Object doTransaction(ProceedingJoinPoint joinPoint)throws Throwable{
        //advice
        try {

            log.info("[트랜잭션 시작] {}",joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}",joinPoint.getSignature());
            return result;
        }
        catch (Exception e)
        {
            log.info("[트랜잭션 롤백] {}",joinPoint.getSignature());
            throw e;
        }
        finally {
            log.info("[리소스 릴리즈] {}",joinPoint.getSignature());
        }
    }
}
