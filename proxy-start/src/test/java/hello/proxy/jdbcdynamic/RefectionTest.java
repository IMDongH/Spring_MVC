package hello.proxy.jdbcdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class RefectionTest {

    @Test
    void reflection0(){
        Hello target = new Hello();

        log.info("start");
        String result1 = target.CallA();
        log.info("result={}",result1);

        log.info("start");
        String result2 = target.CallB();
        log.info("result={}",result2);
    }

    @Test
    void reflection1() throws Exception{
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdbcdynamic.RefectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("CallA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}",result1);

        Method methodCallB = classHello.getMethod("CallB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}",result2);
    }
    @Slf4j
    static class Hello{
        public String CallA(){
            log.info("call A ");
            return "A";
        }

        public String CallB(){
            log.info("call B ");
            return "B";
        }
    }
}
