package com.ignatius.object.tests;

import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import com.ignatius.data.objects.Side;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SideTest {

    @Mock
    Player player;

    Side testSide = createSide();

    @Test
    public void testEnableSide() {
        testSide = createSide();
    }

    @Test
    public void testDisableSide() {
       testSide = createSide();
    }

    @Test
    public void testTurn() {
        // ensure certain methods are called
        // Mockito.verify().
    }

    @Test
    public void TestEmptyPit() {
        Side testSide = createSide();
        testSide.emptyPit(4);
        Pit testPit = testSide.getPit(4);
        testPit.emptyPit();
        assertTrue(testSide.getPit(4).getStones() == 0);
    }

    @Test
    public void testAddToPit() {
    }

    @Test
    public void testAddToKalaha() {
    }

    @Test
    public void testSetAmountPits() {
    }

    @Test
    public void testSetAmountStones() {
    }

    private Side createSide() {
        return new Side(6, 36);
    }
}