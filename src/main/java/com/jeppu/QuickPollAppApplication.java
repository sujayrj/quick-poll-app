package com.jeppu;

import com.jeppu.handler.RestExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestExceptionHandler.class)
public class QuickPollAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickPollAppApplication.class, args);
    }

}
