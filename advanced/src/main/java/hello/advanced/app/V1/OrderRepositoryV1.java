package hello.advanced.app.V1;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    
    //예외 테스트 로직
    public void save(String itemId){

        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생 ! ");
        }
        sleep(1000);
    }

    private void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
