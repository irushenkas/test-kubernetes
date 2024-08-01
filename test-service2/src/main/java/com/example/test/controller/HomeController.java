package com.example.test.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private MeterRegistry registry;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/test1")
    public String getText() {
        logger.info("getText execution");
        registry.counter("test1.call").increment(1);
        return "Hello from Service2";
    }
}
