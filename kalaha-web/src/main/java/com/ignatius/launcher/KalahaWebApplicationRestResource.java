package com.ignatius.launcher;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API for Web Application related tasks
 *
 * @author Ignatius de Villiers
 * @since 14 Dec 2018
 */
@RestController
public class KalahaWebApplicationRestResource implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * Closes the Application and enables the application to shut down gracefully
     *
     * TODO this does not currently work when called from UI
     */
    @RequestMapping(value = "/quit")
    public void quit() {
        ((ConfigurableApplicationContext) context).close();
    }
}
