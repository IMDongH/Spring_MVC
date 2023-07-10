package hello.proxy.pureproxy.decorator.code;


import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TimeDecorator implements Component{


    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();
        String operation = component.operation();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDeco 종료 ={}ms",resultTime);
        return operation;
    }
}
