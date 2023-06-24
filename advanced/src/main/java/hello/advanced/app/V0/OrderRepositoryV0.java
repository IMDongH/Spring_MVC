package hello.advanced.app.V0;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {


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
