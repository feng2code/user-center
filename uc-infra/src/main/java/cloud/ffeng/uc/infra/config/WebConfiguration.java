package cloud.ffeng.uc.infra.config;

import cloud.ffeng.common.support.TraceContext;
import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.error.web.RestErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = RestErrorHandler.class)
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter((request, response, chain) -> {
            HttpServletRequest req = (HttpServletRequest) request;
            // 初始化TraceId
            String traceId = ((HttpServletRequest) request).getHeader(TraceContext.TRANCE_ID);
            if (StringUtils.isBlank(traceId)) {
                traceId = UuidUtil.generateUuid();
                TraceContext.initTraceId(traceId);
            }
            // 监听耗时
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            try {
                chain.doFilter(request, response);
            } finally {
                stopWatch.stop();
                long ms = stopWatch.getTime(TimeUnit.MILLISECONDS);
                log.info("[Http Request] URI: {}, Method: {}, Cost: {}ms;", req.getRequestURI(), req.getMethod(), ms);
            }
        });
        return filterFilterRegistrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}