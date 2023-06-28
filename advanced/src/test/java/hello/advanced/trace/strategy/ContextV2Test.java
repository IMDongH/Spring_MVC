package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.Strategy.ContextV2;
import hello.advanced.trace.strategy.code.Strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.Strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {


    @Test
    void StrategyV1(){
        ContextV2 context =  new ContextV2();

        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void StrategyV2(){
        ContextV2 context =  new ContextV2();

        context.execute(() -> log.info("비즈니스 로직 1 실행"));
        context.execute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
