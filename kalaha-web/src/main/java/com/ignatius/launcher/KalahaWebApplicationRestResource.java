package com.ignatius.launcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KalahaWebApplicationRestResource {

    // TODO Upgrade spring - this does not currently work
    @RequestMapping("/quit")
    public void quit() {
        System.out.println("I'm closing");
        KalahaWebApplication.quit();
    }
}
