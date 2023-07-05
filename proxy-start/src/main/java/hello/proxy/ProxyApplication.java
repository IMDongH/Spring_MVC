package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(AppV2Config.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
//컴포넌트 스캔의 위치를 지정했는데 이 이유는 config 파일을 바꿔가기위함
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
