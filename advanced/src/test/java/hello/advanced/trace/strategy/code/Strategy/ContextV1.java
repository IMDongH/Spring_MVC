package hello.advanced.trace.strategy.code.Strategy;


import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식이라고 한다.
 */

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();

        strategy.call();
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("result time = {}",resultTime);
    }
}
