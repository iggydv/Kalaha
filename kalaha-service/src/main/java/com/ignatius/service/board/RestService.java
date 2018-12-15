package com.ignatius.service.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class RestService {

    private static Logger logger = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private ResourceLoader loader;

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @RequestMapping(value = "/help", produces = "text/html", method = RequestMethod.GET)
    public String help() {

        Resource resource  = loader.getResource("classpath:files/help.txt");
        StringBuffer helpOutput = new StringBuffer();
        try {
            File file = resource.getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileOutput;
            while ((fileOutput = br.readLine()) != null ) {
                helpOutput.append(fileOutput);
                helpOutput.append(System.lineSeparator());
            }
        } catch (IOException e) {
            logger.error("Could not Read file {}", "help.txt", e);
        }
        return helpOutput.toString();
    }
}
