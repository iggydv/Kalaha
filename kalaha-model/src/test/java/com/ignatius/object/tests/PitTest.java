package com.ignatius.object.tests;
import com.ignatius.data.objects.Pit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PitTest {

    @Test
    public void testEmptyPit() {
        Pit testPit = new Pit(6, false);
        testPit.emptyPit();
        assertEquals(0, testPit.getStones());
    }

    @Test
    public void testReceive() {
        Pit testPit = new Pit(6, false);
        testPit.receive(3);
        assertEquals(9, testPit.getStones());
    }
}