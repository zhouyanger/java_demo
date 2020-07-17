package com.zy.servicegatewaydemo;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

//类名是xxGatewayFilterFactory,只需要一个参数所以继承这个类，RequestTimeGatewayFilterFactory.Config是静态内部类，目的是传key进去判断
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {
    private static final String KEY = "printParam";
    //一定要把config构造器传过去，不然报异常
    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 必须要覆盖这个，把key传进去
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }
    @Override
    public GatewayFilter apply(Config config) {
        //新建匿名内部类，返回一个filter
        return (exchange,chain) -> {
            exchange.getAttributes().put("start_time",System.currentTimeMillis());
            return  chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        if (config.isPrintParam()){
                            //是true就打印
                            Long start_time = (Long) exchange.getAttribute("start_time");
                            System.out.println(exchange.getRequest().getURI().getPath() + " 耗时:" + (System.currentTimeMillis() - start_time) + "ms");
                        }
                       
                    })
            );
        };
    }

    public static class Config{
        private  boolean printParam;

        public boolean isPrintParam() {
            return printParam;
        }

        public void setPrintParam(boolean printParam) {
            this.printParam = printParam;
        }
    }
}
