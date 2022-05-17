package br.com.oneos.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class BaseIT {

    @Autowired
    @Qualifier(value = "testRestTemplateCustom")
    protected TestRestTemplate testRestTemplate;

    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplateCustom")
        public TestRestTemplate testRestTemplateCustom(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port + "/poc-ms-hexagonal/api/v1");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }
}
