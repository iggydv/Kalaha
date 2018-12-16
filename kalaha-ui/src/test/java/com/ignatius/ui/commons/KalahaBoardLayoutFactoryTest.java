package com.ignatius.ui.commons;

import com.ignatius.data.objects.Board;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.ui.Component;
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

    @InjectMocks
    BoardService boardService;

    @InjectMocks
    @Autowired
    KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

    @Test
    public void testBoardUIInitialization() {
        Component board = createBoardLayout();
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
    public void woah() {
        KalahaBoardLayoutFactory.BoardLayout board = createBoardLayout();
        board.init().layout().setClickerListeners();

        board.createWinnerPopUp(BoardStringUtils.PLAYER_1);
    }

    private KalahaBoardLayoutFactory.BoardLayout createBoardLayout() {
        KalahaBoardLayoutFactory.BoardLayout board = kalahaBoardLayoutFactory.createComponent(boardService);
        return board;
    }
}