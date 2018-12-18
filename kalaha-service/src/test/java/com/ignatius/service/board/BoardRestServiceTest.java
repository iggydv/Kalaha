package com.ignatius.service.board;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardRestServiceTest {

    BoardRestService boardRestService = new BoardRestService();

    @Test
    public void TestHelpReturned() {
        String output = boardRestService.help();
        assertNotNull(output);
    }

}