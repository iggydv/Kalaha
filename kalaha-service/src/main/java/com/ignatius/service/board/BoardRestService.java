package com.ignatius.service.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * This REST service provides a single REST endpoint, that reads from the help.txt file, to be used as guidance for the
 * players.
 *
 * @author Ignatius de Villiers
 * @since 16 December 2018
 *
 * @note (From author) This class is definitely not required to read text from a file, it just provides a simple use case
 * of the Spring @{@link RestController}
 *
 */
@RestController
public class BoardRestService {

    private static Logger logger = LoggerFactory.getLogger(BoardRestService.class);

    @Autowired
    private ResourceLoader loader;

    @RequestMapping(value = "/help", produces = "text/html", method = RequestMethod.GET)
    public String help() {
        logger.info("Reading from help.txt");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("files/help.txt");
        String output = null;
        try {
            output = convertStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    private String convertStreamToString(InputStream inputStream) throws IOException {
        logger.debug("Converting Inputstream to String");
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
