package com.atguigu.cloud.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义会员等级userType 按照金/钻/银和yml配置的会员等级 以适配是否可以访问
 *
 * @author machaochen
 * @date 2024/03/07
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public static final String USER_TYPE_KEY = "userType";

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(USER_TYPE_KEY);
    }
    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return (serverWebExchange -> {
            //检查request的参数里面 userType是否为指定的值 符合配置就通过
            String userType = serverWebExchange.getRequest().getQueryParams().getFirst(USER_TYPE_KEY);

            if (userType == null) return false;

            //如果说参数存在 就和config的数据进行比较
            if (userType.equals(config.getUserType())) {
                return true;
            }
            return false;
        });
    }
    @Validated
    public static class Config {
        @Getter
        @Setter
        @NotEmpty
        private String userType;
    }
}
