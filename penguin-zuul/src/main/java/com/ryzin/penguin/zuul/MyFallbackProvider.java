package com.ryzin.penguin.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 熔断处理类
 * @author Ryzin
 * 实现熔断器功能
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() { // 返回"penguin-consumer"，只针对consumer服务进行熔断。
    	// 如果需要所有的路由服务都加熔断功能，需要在getRoute()方法上返回”*“的匹配符
        return "penguin-consumer";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) { // 进入熔断器功能时执行的逻辑
        System.out.println("route:"+route);
        System.out.println("exception:"+cause.getMessage());
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException { // 返回发送熔断时的反馈信息
                return new ByteArrayInputStream("Sorry, the service is unavailable now.".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
