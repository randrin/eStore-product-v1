package com.eStore.bear.product.configuration;

import com.eStore.bear.product.filters.RequestResponseLoggers;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    FilterRegistrationBean<RequestResponseLoggers> createLoggers (RequestResponseLoggers requestResponseLoggers) {
        FilterRegistrationBean<RequestResponseLoggers> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(requestResponseLoggers);
        filterRegistrationBean.addUrlPatterns("/v1/addProduct", "/v1/product/*", "/v1/productList/*");

        return filterRegistrationBean;
    }
}
