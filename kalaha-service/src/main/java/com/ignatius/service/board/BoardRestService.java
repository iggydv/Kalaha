package com.ignatius.service.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * This REST API consists of a single REST endpoint, that reads from the help.txt file, to be used as guidance for the
 * players.
 *
 * @author Ignatius de Villiers
 * @since 16 December 2018
 *
 * @note (from @author) This class is definitely not required to read text from a file, it just provides a simple use case
 * of the Spring @{@link RestController}
 *
 */
@RestController
public class BoardRestService {

    private static Logger logger = LoggerFactory.getLogger(BoardRestService.class);

    @Autowired
    private ResourceLoader loader;

    /**
     * @return String copy of the content found in help.txt
     */
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

    /**
     * @param inputStream to be converted to string
     * @return String representation of the @{@link InputStream}
     * @throws IOException
     */
    private String convertStreamToString(InputStream inputStream) throws IOException {
        logger.debug("Converting Inputstream to String");
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8));
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
