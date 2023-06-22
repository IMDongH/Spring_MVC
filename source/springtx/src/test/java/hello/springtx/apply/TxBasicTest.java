package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class TxBasicTest {

    static class BasicService{

        @Transactional
        public void tx(){
            log.info("call tx");

        }

    }
}
