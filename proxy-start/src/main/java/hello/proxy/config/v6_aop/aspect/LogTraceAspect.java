package hello.proxy.config.v6_aop.aspect;


import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    //advisor 생성 (pointcut + advice)
    @Around("execution(* hello.proxy.app..*(..))") //pointcut
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        //advice 로직이 들어간다

        TraceStatus status = null;
        try{
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
