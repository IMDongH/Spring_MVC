package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {//서블릿으로 implements 해야함
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST[{}][{}]",requestURI,uuid);
            chain.doFilter(request,response);// 다음 필터가 있으면 다음 필터 실행 없으면 서블릿 실행
        }
        catch (Exception e){
            throw e;
        }
        finally {
            log.info("RESPONSE[{}][{}]",requestURI,uuid);

        }


    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
        Filter.super.destroy();
    }

}
