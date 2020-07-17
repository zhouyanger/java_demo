package com.zy.servicegatewaydemo;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MyGatewayFilter implements GatewayFilter, Ordered {
    //记录请求耗时，chain.filter之前是pre过滤，and()是post过滤
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("start_time",System.currentTimeMillis());
       return  chain.filter(exchange).and(Mono.fromRunnable(() ->{
           Long start_time = (Long)exchange.getAttribute("start_time");
           System.out.println(exchange.getRequest().getURI().getPath()+" 耗时:"+(System.currentTimeMillis()-start_time)+"ms");
       }));
    }

    //值越大，优先级越低
    @Override
    public int getOrder() {
        return 0;
    }
}
