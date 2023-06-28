package hello.advanced.trace.strategy.code.Strategy;


import lombok.extern.slf4j.Slf4j;

/**
 * 파라미터로 전략을 전달하는 방식이라고 한다.
 * 선조립 후 실행 하는 방식이 아니라 실행하는 시점에 원하는 Strategy를 전달할 수 있음 -> 유연하게 변경 가능, 하나의 컨텍스트만 실행해서 여러 전략 실행 가능
 */

@Slf4j
public class ContextV2 {


    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        strategy.call();
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("result time = {}",resultTime);
    }
}
