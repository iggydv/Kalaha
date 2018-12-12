package com.ignatius.ui.commons;

import com.ignatius.data.objects.Board;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
//import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@org.springframework.stereotype.Component
public class KalahaRegisterPlayerLayoutFactory implements UIComponentBuilder {
    private static Logger logger = LoggerFactory.getLogger(KalahaRegisterPlayerLayoutFactory.class);
    BoardService boardService;

    private class RegisterPlayerLayout extends VerticalLayout {
        // ui objects
        TextField player1;
        TextField player2;
        Button lockPlayer1;
        Button lockPlayer2;
        Button start;

        public RegisterPlayerLayout init() {
            player1 = new TextField(BoardStringUtils.PLAYER_1.getString());
            player1.setWidth("50%");
            player2 = new TextField(BoardStringUtils.PLAYER_2.getString());
            player2.setWidth("50%");
            lockPlayer1 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer1.setWidth("50%");
            lockPlayer2 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer2.setWidth("50%");
            start = new Button(BoardStringUtils.START_GAME.getString());
            start.setWidth("50%");
            return this;
        }

        public RegisterPlayerLayout layout() {
            addComponent(player1);
            addComponent(lockPlayer1);
            addComponent(player2);
            addComponent(lockPlayer2);
            addComponent(start);
            setComponentAlignment(player1, Alignment.MIDDLE_CENTER);
            setComponentAlignment(player2, Alignment.MIDDLE_CENTER);
            setComponentAlignment(lockPlayer1, Alignment.TOP_CENTER);
            setComponentAlignment(lockPlayer2, Alignment.TOP_CENTER);
            setComponentAlignment(start, Alignment.BOTTOM_CENTER);

            return this;
        }

        public RegisterPlayerLayout setClickerListeners() {
            player1.addValueChangeListener(event -> {
                player1.setValue("iggy-pop");
            });
            player2.addValueChangeListener(event -> {
                player2.setValue("clara-pop");
            });
            player1.setValue("iggy-pop");
            player2.setValue("clara-pop");
            lockPlayer1.addClickListener((ClickEvent event) -> {

                System.out.println("text: "+player1.getValue());
                if (!player1.isEmpty()) {
                    boardService.assignPlayerNames(player1.getValue());
                    lockPlayer1.setEnabled(false);
                } else {
                    try {
                        throw new IOException("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            lockPlayer2.addClickListener((ClickEvent event) -> {
                System.out.println("text: "+player2.getValue());
                if (!player2.isEmpty()) {
                    boardService.assignPlayerNames(player2.getValue());
                    lockPlayer2.setEnabled(false);
                } else {
                    try {
                        throw new IOException("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            start.addClickListener((ClickEvent event) -> {
                if (boardService.startGame()) {
                    Window w = this.findAncestor(Window.class);
                    w.close();
                }
            });
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new RegisterPlayerLayout().init().layout().setClickerListeners();
    }

    public Component createComponent(BoardService boardService) {
        this.boardService = boardService;
        return createComponent();
    }
}
