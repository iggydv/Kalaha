package com.ignatius.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;


/**
 * @author Ignatius de Villiers
 * @since 14 December 2018
 */
@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan({"com.ignatius"})
@SpringBootApplication
public class KalahaWebApplication {

    private static Logger logger = LoggerFactory.getLogger(KalahaWebApplication.class);
    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        logger.info("Initializing Kalaha web application");
        ctx = SpringApplication.run(KalahaWebApplication.class, args);
        logger.debug("Application context configured");
    }

    @PreDestroy
    public void onShutDown() {
        logger.warn("closing application context");
    }
}
