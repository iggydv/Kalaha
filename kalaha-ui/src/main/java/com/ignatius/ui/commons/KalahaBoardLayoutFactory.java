package com.ignatius.ui.commons;

// builder pattern

import com.ignatius.utils.BoardStringUtils;
import com.vaadin.ui.*;

@org.springframework.stereotype.Component
public class KalahaBoardLayoutFactory implements UIComponentBuilder {

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

        //Panel gamePanel;
        //HorizontalLayout boardLayout;
        HorizontalLayout playerOneSide;
        HorizontalLayout playerTwoSide;
        VerticalLayout pitsLayout;

        // hard coded array
        Button[] buttons = new Button[]{pit0, pit1, pit2, pit3, pit4, pit5, pit6, pit7, pit8, pit9, pit10, pit11};

        public  BoardLayout init() {
            // Panel should be created in main UI
            //gamePanel = new Panel();
            //boardLayout = new HorizontalLayout();
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
//            pit0 = new Button("0");
//            pit0.setHeight("50%");
//            pit0.addClickListener((Button.ClickEvent event) -> {
//                kalahaPlayer1.setCaption("blep");
//            });
//
//            pit1 = new Button("1");
//            pit1.setHeight("50%");
//
//            pit2 = new Button("2");
//            pit2.setHeight("50%");
//
//            pit3 = new Button("3");
//            pit3.setHeight("50%");
//
//            pit4 = new Button("4");
//            pit4.setHeight("50%");
//
//            pit5 = new Button("5");
//            pit5.setHeight("50%");
//
//            pit6 = new Button("6");
//            pit6.setHeight("50%");
//
//            pit7 = new Button("7");
//            pit7.setHeight("50%");
//
//            pit8 = new Button("8");
//            pit8.setHeight("50%");
//
//            pit9 = new Button("9");
//            pit9.setHeight("50%");
//
//            pit10 = new Button("10");
//            pit10.setHeight("50%");
//
//            pit11 = new Button("11");
//            pit11.setHeight("50%");
        }

        private void createBoardPanel() {
//            Panel gamePanel = new Panel();
//            gamePanel.setWidth("100%");
//            gamePanel.setHeight("100%");

//            HorizontalLayout boardLayout = new HorizontalLayout();
//            boardLayout.setSizeFull();
//            boardLayout.setMargin(true);
//
//            VerticalLayout pitsLayout = new VerticalLayout();
//            pitsLayout.setSizeFull();
//            pitsLayout.setMargin(true);
//
//            HorizontalLayout playerOneSide = new HorizontalLayout();
//            playerOneSide.setSizeFull();
//            //playerOneSide.setMargin(true);
//            playerOneSide.setSpacing(true);
//            createPlayerOneSide(playerOneSide);
//            pitsLayout.addComponent(playerOneSide);
//
//            HorizontalLayout playerTwoSide = new HorizontalLayout();
//            playerTwoSide.setSizeFull();
            //playerTwoSide.setMargin(true);
//            playerTwoSide.setSpacing(true);
//            createPlayerTwoSide(playerTwoSide);
//            pitsLayout.addComponent(playerTwoSide);
//
//            pitsLayout.setComponentAlignment(playerOneSide, Alignment.MIDDLE_CENTER);
//            pitsLayout.setComponentAlignment(playerTwoSide, Alignment.MIDDLE_CENTER);
//
//            boardLayout.addComponent(kalahaPlayer1);
//            boardLayout.addComponent(pitsLayout);
//            boardLayout.addComponent(kalahaPlayer2);
//            boardLayout.setComponentAlignment(pitsLayout, Alignment.MIDDLE_CENTER);
//            boardLayout.setComponentAlignment(kalahaPlayer1, Alignment.TOP_RIGHT);
//            boardLayout.setComponentAlignment(kalahaPlayer2, Alignment.TOP_LEFT);
//
//            //find positions
//            quit = new Button(BoardStringUtils.QUIT.getString());
//            reset = new Button(BoardStringUtils.RESET.getString());
//            rules = new Button(BoardStringUtils.GAME_RULES.getString());
//
//            gamePanel.setContent(boardLayout);
//            rootLayout.addComponent(gamePanel);
//            rootLayout.setExpandRatio(gamePanel, 5);
//            rootLayout.setComponentAlignment(gamePanel, Alignment.MIDDLE_CENTER);
        }

        private void createPlayerOneSide(HorizontalLayout layout) {
            pit0 = new Button("0");
            pit0.setHeight("50%");
            pit0.addClickListener((Button.ClickEvent event) -> {
                kalahaPlayer1.setCaption("blep");
            });
            layout.addComponent(pit0);
            pit1 = new Button("1");
            pit1.setHeight("50%");
            layout.addComponent(pit1);
            pit2 = new Button("2");
            pit2.setHeight("50%");
            layout.addComponent(pit2);
            pit3 = new Button("3");
            pit3.setHeight("50%");
            layout.addComponent(pit3);
            pit4 = new Button("4");
            pit4.setHeight("50%");
            layout.addComponent(pit4);
            pit5 = new Button("5");
            pit5.setHeight("50%");
            layout.addComponent(pit5);
        }

        private void createPlayerTwoSide(HorizontalLayout layout) {
            pit6 = new Button("6");
            pit6.setHeight("50%");
            layout.addComponent(pit6);
            pit7 = new Button("7");
            pit7.setHeight("50%");
            layout.addComponent(pit7);
            pit8 = new Button("8");
            pit8.setHeight("50%");
            layout.addComponent(pit8);
            pit9 = new Button("9");
            pit9.setHeight("50%");
            layout.addComponent(pit9);
            pit10 = new Button("10");
            pit10.setHeight("50%");
            layout.addComponent(pit10);
            pit11 = new Button("11");
            pit11.setHeight("50%");
            layout.addComponent(pit11);
        }

        public BoardLayout layout() {

            for (int i = 0; i < 6; i++) {
                playerOneSide.addComponent(buttons[i]);
            }

            for (int i = 6; i < buttons.length; i++) {
                playerTwoSide.addComponent(buttons[i]);
            }

            pitsLayout.addComponent(playerOneSide);
            pitsLayout.addComponent(playerTwoSide);
            pitsLayout.setComponentAlignment(playerOneSide, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(playerTwoSide, Alignment.MIDDLE_CENTER);
            addComponent(kalahaPlayer1);
            addComponent(pitsLayout);
            addComponent(kalahaPlayer2);
            setComponentAlignment(pitsLayout, Alignment.MIDDLE_CENTER);
            setComponentAlignment(kalahaPlayer1, Alignment.TOP_RIGHT);
            setComponentAlignment(kalahaPlayer2, Alignment.TOP_LEFT);
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new BoardLayout().init().layout();
    }




}
