package hello.proxy.proxyfactoty;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있는 경우 JDK 동적 프록시를 사용함")
    void interfaceProxy(){
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("target = {}",target.getClass());
        log.info("proxy = {}",proxy.getClass());

        proxy.save();

        //proxyFactory를 통해 만들엇을때만 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); //인터페이스
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse(); //구현체
    }
    @Test
    @DisplayName("구체 클래스 있는 경우 CGLIB 사용함")
    void concreteProxy(){
        ConcreteService target = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());

        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("target = {}",target.getClass());
        log.info("proxy = {}",proxy.getClass());

        proxy.call();

        //proxyFactory를 통해 만들엇을때만 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse(); //인터페이스
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); //구현체
    }


    @Test
    @DisplayName("ProxyTargetClass 옵션 사용 시 인터페이스가 있어도 CGLIB 를 사용하고 클래스 기반")
    void proxyTargetClass(){
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.setProxyTargetClass(true); //인터페이스가 있어도 CGLIB 기반으로 동작

        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("target = {}",target.getClass());
        log.info("proxy = {}",proxy.getClass());

        proxy.save();

        //proxyFactory를 통해 만들엇을때만 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse(); //인터페이스
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); //구현체
    }
}
