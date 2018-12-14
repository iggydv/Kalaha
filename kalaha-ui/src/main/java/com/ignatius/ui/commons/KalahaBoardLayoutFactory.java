package com.ignatius.ui.commons;

// builder pattern

import com.ignatius.data.objects.Pit;
import com.ignatius.service.board.BoardService;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;


@org.springframework.stereotype.Component
public class KalahaBoardLayoutFactory implements UIComponentBuilder {

    BoardService boardService;

    private class BoardLayout extends HorizontalLayout {

        Button kalahaPlayer1;
        Button kalahaPlayer2;
        Button pit0 = new Button("0");
        Button pit1 = new Button("1");
        Button pit2 = new Button("2");
        Button pit3 = new Button("3");
        Button pit4 = new Button("4");
        Button pit5 = new Button("5");
        Button pit6 = new Button("6");
        Button pit7 = new Button("7");
        Button pit8 = new Button("8");
        Button pit9 = new Button("9");
        Button pit10 = new Button("10");
        Button pit11 = new Button("11");
        Button quit;
        Button reset;
        Button rules;

        HorizontalLayout playerOneSide;
        HorizontalLayout playerTwoSide;
        VerticalLayout pitsLayout;

        // hard coded array
        Button[] pitButtons = new Button[]{pit5, pit4, pit3, pit2, pit1, pit0, pit6, pit7, pit8, pit9, pit10, pit11};

        public BoardLayout init() {
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

            for (Button pitButton : pitButtons) {
                pitButton.setHeight("50%");
                pitButton.setId(pitButton.getCaption());
            }

            return this;
        }

        public BoardLayout layout() {

            // TODO: Can to with array split
            for (int i = 0; i < 6; i++) {
                playerOneSide.addComponent(pitButtons[i]);
                playerOneSide.setComponentAlignment(pitButtons[i], Alignment.BOTTOM_CENTER);
            }

            for (int i = 6; i < pitButtons.length; i++) {
                playerTwoSide.addComponent(pitButtons[i]);
                playerTwoSide.setComponentAlignment(pitButtons[i], Alignment.TOP_CENTER);
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

            //
            System.out.println("dsf,dnlfskmdf");
            updatePitButtons(boardService.getBoard().getPits());
            System.out.println("dsf,dnldkmdf");

            return this;
        }

        public BoardLayout setClickerListeners() {
            for (Button pitButton : pitButtons) {
                pitButton.addClickListener((ClickEvent event) -> {
                    Pit[] boardPitArray = boardService.play(Integer.parseInt(pitButton.getId()));
                    updateUI(boardService.getActivePlayerNumber(), boardPitArray);
                    if (boardService.isGameEnd()) {
                       // TODO add a popup here boi
                    }
                });
            }
            return this;
        }

        private void updateUI(int activePlayerNumber, Pit[] boardPitArray) {
            switch (activePlayerNumber) {
                case 1: disablePlayer2Pits(); break;
                case 2: disablePlayer1Pits(); break;
                default: throw new IllegalArgumentException("Player unkown");
            }
            updatePitButtons(boardPitArray);
            kalahaPlayer1.setCaption(""+boardService.getKalahaStones(1));
            kalahaPlayer2.setCaption(""+boardService.getKalahaStones(2));
        }

        private void updatePitButtons(Pit[] boardPitArray) {
            for (Button pit : pitButtons) {
                int index = Integer.parseInt(pit.getId());
                pit.setCaption(""+boardPitArray[index].getStones());
            }
        }

        private void disablePlayer2Pits() {
            for (Button pit : pitButtons) {
                if (Integer.parseInt(pit.getId()) >= 6) {
                    pit.setEnabled(false);
                } else {
                    pit.setEnabled(true);
                }

            }
        }

        private void disablePlayer1Pits() {
            for (Button pit : pitButtons) {
                if (Integer.parseInt(pit.getId()) < 6) {
                    pit.setEnabled(false);
                } else {
                    pit.setEnabled(true);
                }

            }
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
