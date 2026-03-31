package com.ubisam.exam16;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ubisam.exam16.domain.Route;
import com.ubisam.exam16.domain.Train;

import io.u2ware.common.data.jpa.config.EnableRestfulJpaRepositories;

@Configuration
@EnableRestfulJpaRepositories
@EnableJpaRepositories
public class ApplicationApiConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // 초반 경로 설정
        config.setBasePath("/api");

        // 응답에 ID 찍기
        config.exposeIdsFor(Train.class, Route.class);

        // Post와 Put을 위한 Body를 리턴
        config.setReturnBodyForPutAndPost(true);

        // addMapping("/**") : 모든 URL 경로에 CORS 설정 적용
        // allowedOrigins("*") : 모든 출처(도메인)에서 오는 요청을 허용
        // allowedMethods("*") : 모든 HTTP 메서드를 허용
        // allowCredentials(false) : 쿠키나 인증 정보를 요청에 포함하지 않음 (allowedOrigins("*")랑 같이 쓰려면
        // 반드시 false 여야함.)
        // maxAge(999999) : 브라우저가 CORS 허용 정보를 캐싱(기억)하는 시간
        cors.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowCredentials(false).maxAge(999999);

    }

}
