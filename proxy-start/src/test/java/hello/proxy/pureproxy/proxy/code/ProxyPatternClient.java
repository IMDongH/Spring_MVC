package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternClient {
//프록시 패턴 적용 전
    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute(){
        subject.operation();
    }
}
