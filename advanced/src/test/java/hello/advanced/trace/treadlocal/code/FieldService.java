package hello.advanced.trace.treadlocal.code;


import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class FieldService {

    private String nameStore;

    public String logic(String name) throws InterruptedException {
        log.info("저장 name={}-> nameStore={}",name,nameStore);

        nameStore=name;
        sleep(1000);

    }
}
