package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.Strategy.ContextV1;
import hello.advanced.trace.strategy.code.Strategy.Strategy;
import hello.advanced.trace.strategy.code.Strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.Strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 1 실행");

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("result time = {}",resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 2 실행");

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("result time = {}",resultTime);
    }

    /**
     * 전략패턴 적용
     */
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void strategyV2(){
        Strategy strategy1 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();

        Strategy strategy2 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }


    /**
     * 람다 사용하려면 인터페이스 메서드가 1개만 존재해야한다. 현재는 1개만 존재하므로 람다 사용 가능.
     */
    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        contextV2.execute();
    }
}
