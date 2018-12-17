package com.ignatius.launcher;

import org.apache.catalina.Executor;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class KalahaWebApplicationRestResource implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    // TODO Upgrade spring - this does not currently work
    @RequestMapping(value = "/quit")
    public void quit() {
        ((ConfigurableApplicationContext) context).close();
    }
}
