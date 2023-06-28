package hello.advanced.trace.treadlocal;

import hello.advanced.trace.treadlocal.code.FieldService;
import hello.advanced.trace.treadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    private ThreadLocalService fieldService = new ThreadLocalService();


    @Test
    void field(){
        log.info("main start");

//        Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
        /**
         * 위 코드와 같은 코
         */
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); //동시성 문제 발생하지 않는 코드
        sleep(100); //동시성 문제 발생하는 코드
        threadB.start();

        sleep(2000); //메인 쓰레드 종료 대기

        log.info("main exit");
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
    }
}