package com.ignatius.ui.commons;

// builder pattern

import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;


@org.springframework.stereotype.Component
public class KalahaBoardLayoutFactory implements UIComponentBuilder {

    BoardService boardService;

    private class BoardLayout extends HorizontalLayout {

        Button kalahaPlayer1;
        Button kalahaPlayer2;
        Button pit0 = new Button();
        Button pit1 = new Button();
        Button pit2 = new Button();
        Button pit3 = new Button();
        Button pit4 = new Button();
        Button pit5 = new Button();
        Button pit6 = new Button();
        Button pit7 = new Button();
        Button pit8 = new Button();
        Button pit9 = new Button();
        Button pit10 = new Button();
        Button pit11 = new Button();
        Button quit;
        Button reset;
        Button rules;

        HorizontalLayout playerOneSide;
        HorizontalLayout playerTwoSide;
        VerticalLayout pitsLayout;

        // hard coded array
        Button[] buttons = new Button[]{pit0, pit1, pit2, pit3, pit4, pit5, pit6, pit7, pit8, pit9, pit10, pit11};

        public  BoardLayout init() {
            pitsLayout = new VerticalLayout();
            pitsLayout.setSizeFull();
            pitsLayout.setMargin(true);

            playerOneSide = new HorizontalLayout();
            playerOneSide.setSizeFull();
            playerOneSide.setSpacing(true);

            playerTwoSide = new HorizontalLayout();
            playerTwoSide.setSizeFull();
            playerTwoSide.setSpacing(true);

            kalahaPlayer1 = new Button("kalaha player 1");
            kalahaPlayer1.setHeight("80%");
            kalahaPlayer1.setWidth("30%");
            kalahaPlayer1.setEnabled(false);

            kalahaPlayer2 = new Button("kalaha player 2");
            kalahaPlayer2.setHeight("80%");
            kalahaPlayer2.setWidth("30%");
            kalahaPlayer2.setEnabled(false);

            int buttonNumber = 0;
            for (Button pitButton : buttons) {
                pitButton.setCaption(""+buttonNumber);
                pitButton.setHeight("50%");
                buttonNumber++;
            }

            return this;
        }

        public BoardLayout layout() {

            for (int i = 0; i < 6; i++) {
                playerOneSide.addComponent(buttons[i]);
                playerOneSide.setComponentAlignment(buttons[i], Alignment.BOTTOM_CENTER);
            }

            for (int i = 6; i < buttons.length; i++) {
                playerTwoSide.addComponent(buttons[i]);
                playerTwoSide.setComponentAlignment(buttons[i], Alignment.TOP_CENTER);
            }

            pitsLayout.addComponent(playerOneSide);
            pitsLayout.addComponent(playerTwoSide);
            pitsLayout.setComponentAlignment(playerOneSide, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(playerTwoSide, Alignment.MIDDLE_CENTER);
            addComponent(kalahaPlayer1);
            addComponent(pitsLayout);
            addComponent(kalahaPlayer2);
            setComponentAlignment(pitsLayout, Alignment.MIDDLE_CENTER);
            setComponentAlignment(kalahaPlayer1, Alignment.MIDDLE_RIGHT);
            setComponentAlignment(kalahaPlayer2, Alignment.MIDDLE_LEFT);
            return this;
        }

        public BoardLayout setClickerListeners() {
            pit0.addClickListener((ClickEvent event) -> {
                boardService.play( boardService.getPlayer1().getPlayerName(), 0);
            });

            pit1.addClickListener((ClickEvent event) -> {
                boardService.play( boardService.getPlayer1().getPlayerName(), 1);
            });

            pit2.addClickListener((ClickEvent event) -> {
                boardService.play( boardService.getPlayer1().getPlayerName(), 2);
            });
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new BoardLayout().init().layout().setClickerListeners();
    }

    public Component createComponent(BoardService boardService) {
        this.boardService = boardService;
        return createComponent();
    }


}
