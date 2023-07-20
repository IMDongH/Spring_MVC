package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}",helloMethod);
    }

    @Test
    void exactMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }


    @Test
    void allMatch() {
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch(){

            //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
            //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
            pointcut.setExpression("execution(* hello(..))");
            assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void packageMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatch2(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage1(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop..*.*(..))");
        // .. -> 해당 패키지와 하위
        // . -> 해당 패키지
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    @Test
    void typeExactMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        // .. -> 해당 패키지와 하위
        // . -> 해당 패키지
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatchSuperType(){ //부모 타입도 허용
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        // .. -> 해당 패키지와 하위
        // . -> 해당 패키지
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatchInternal() throws NoSuchMethodException { //부모 타입도 허용 - 부모에 있는 메서드만 가능하다.
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자  반환 타입              선언타입                     .메서드이름 파라미터
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal",String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    //String 타입의 파라미터만 허용(String)
    @Test
    void argsMatch(){
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //파라미터 X
    @Test
    void argsMatchNoArgs(){
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //정확히 1개의 파라미터
    @Test
    void argsMatchStar(){
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //숫자와 무관하게 몯ㄴ 파라미터 모든 타입 허용
    @Test
    void argsMatchAll(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //String 으로 시작 이후는 무관
    @Test
    void argsMatchComplex(){
        pointcut.setExpression("execution(* *(String,..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}

