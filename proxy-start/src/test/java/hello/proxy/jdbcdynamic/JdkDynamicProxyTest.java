package hello.proxy.jdbcdynamic;

import hello.proxy.jdbcdynamic.code.AImpl;
import hello.proxy.jdbcdynamic.code.AInterface;
import hello.proxy.jdbcdynamic.code.BInterface;
import hello.proxy.jdbcdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.swing.text.TabExpander;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AImpl target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call();

        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy .getClass());
    }

}
