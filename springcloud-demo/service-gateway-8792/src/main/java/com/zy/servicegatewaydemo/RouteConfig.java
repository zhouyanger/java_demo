package com.zy.servicegatewaydemo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.lang.management.MonitorInfo;

//@Configuration
public class RouteConfig {
//    @Bean
//    public RouteLocator myRoutLocator(RouteLocatorBuilder builder){
//      return   builder.routes()
//                .route(p -> p.path("/test/**")
//                .filters(f -> f.filter(new MyGatewayFilter()).addRequestParameter("age","3"))
//                .uri("lb://eureka-provider"))
//                .build();
//    }
}
