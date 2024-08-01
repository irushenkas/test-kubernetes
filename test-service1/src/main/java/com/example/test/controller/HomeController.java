package com.example.test.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    @Autowired
    private MeterRegistry registry;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Value("${service.url}")
    private String serviceUrl;

    @GetMapping
    public String getServiceUrl() {
        return serviceUrl;
    }

    @GetMapping("/test2")
    public Mono<String> getText() {
        logger.info("getText execution");
        registry.counter("test2.call").increment(1);
        return WebClient.create(serviceUrl).get()
                .retrieve()
                .bodyToMono(String.class);
    }
}
