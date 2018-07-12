package com.demo;

import com.demo.Common.Filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.RequestContextFilter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
class DemoApplication {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //    @Bean
//    public RequestContextFilter requestContextFilter(){
//        return new RequestContextFilter();
//    }
    @Bean
    public FilterRegistrationBean requestContextFilter() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new RequestContextFilter());
        filterRegBean.addUrlPatterns("*");
        filterRegBean.setEnabled(Boolean.TRUE);
        filterRegBean.setName("RequestContextFilter");
        return filterRegBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TokenFilter httpBearerFilter = new TokenFilter();
        registrationBean.setFilter(httpBearerFilter);
        List<String> urlPatterns = new ArrayList();
        urlPatterns.add("/token/xx");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
