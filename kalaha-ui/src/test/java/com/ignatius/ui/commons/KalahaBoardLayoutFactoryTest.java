package com.ignatius.ui.commons;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Player;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class KalahaBoardLayoutFactoryTest {

    @Mock
    Board board;

    @Mock
    Player player1, player2;

    @InjectMocks
    BoardService boardService;

    @InjectMocks
    @Autowired
    KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

    @Test
    public void testBoardUIInitialization() {
        createBoardLayout();
    }

    @Test
    public void testInit() {
        KalahaBoardLayoutFactory.BoardLayout board = createBoardLayout();
        board.init();
    }

    @Test
    public void testLayout() {
        KalahaBoardLayoutFactory.BoardLayout board = createBoardLayout();
        board.layout();
    }

    @Test
    public void testSetClickerListeners() {
        KalahaBoardLayoutFactory.BoardLayout board = createBoardLayout();
        board.setClickerListeners();
    }

    @Test
    public void testCreateWinnerPopUp() {
        KalahaBoardLayoutFactory.BoardLayout board = createBoardLayout();
        board.init().layout().setClickerListeners();
        board.createWinnerPopUp(BoardStringUtils.PLAYER_1);
    }

    private KalahaBoardLayoutFactory.BoardLayout createBoardLayout() {
        boardService.assignPlayerOne("player1");
        boardService.assignPlayerTwo("player2");
        KalahaBoardLayoutFactory.BoardLayout board = kalahaBoardLayoutFactory.createComponent(boardService);
        return board;
    }
}