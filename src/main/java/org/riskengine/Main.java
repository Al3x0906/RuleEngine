package org.riskengine;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Main.class);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}

