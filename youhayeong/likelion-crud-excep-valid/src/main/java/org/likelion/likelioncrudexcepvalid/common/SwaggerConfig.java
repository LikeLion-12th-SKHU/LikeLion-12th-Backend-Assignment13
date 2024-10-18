package org.likelion.likelioncrudexcepvalid.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo()); // 바로 뒤에 만들 예정이라 빨간줄이어도 상관 없음
    }
    // info 임포트는 oas.models.info.Info로
    private Info apiInfo() {
        return new Info()
                .title("Swagger 테스트") // api의 제목
                .description("Swagger 설명") // api 설명
                .version("1.0.0"); // api 버전
    }
}