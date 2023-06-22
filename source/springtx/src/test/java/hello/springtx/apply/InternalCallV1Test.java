package hello.springtx.apply;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.invoke.CallSite;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {


    @Autowired
    CallService callService;

    @Test
    public void printProxy() throws Exception{
        //given
        log.info("callService class={}",callService.getClass());
    }

    @Test
    public void internalCall() throws Exception{
        //given
    callService.internal();
    }

    @Test
    public void externalCall() throws Exception{
        //given
        callService.external();
    }


    @TestConfiguration
    static class InternalCallV1TestConfig{

        @Bean
        CallService callService(){
            return new CallService();
        }
    }

    @Slf4j
    static class CallService {

        public void external(){
            log.info("Call external");
            printTxInfo();
            internal();

        }

        @Transactional
        public void internal(){
            log.info("call internal");

            printTxInfo();
        }

        private void printTxInfo(){
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("active = {}",isActive);

            boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

            log.info("isReadOnly = {}",isReadOnly);
        }
    }


}
