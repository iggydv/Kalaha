package com.ignatius.ui.commons;

// builder pattern
import com.ignatius.data.objects.Pit;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Provides the BoardLayout @{@link Component} to the @{@link KalahaUI}
 * Uses the {@link BoardService} class for all backend functionality
 *
 * Pattern used: Builder pattern
 *
 * @author Ignatius de Villiers
 * @since 14 Dec 2018
 */
@org.springframework.stereotype.Component
public class KalahaBoardLayoutFactory implements UIComponentBuilder {

    BoardService boardService;

    private class BoardLayout extends HorizontalLayout {

        private Button kalahaPlayer1;
        private Button kalahaPlayer2;
        private Button pit0;
        private Button pit1;
        private Button pit2;
        private Button pit3;
        private Button pit4;
        private Button pit5;
        private Button pit6;
        private Button pit7;
        private Button pit8;
        private Button pit9;
        private Button pit10;
        private Button pit11;
        private Button reset;
        private Button[] pitButtons;

        private HorizontalLayout playerOneSide;
        private HorizontalLayout playerTwoSide;
        private VerticalLayout pitsLayout;

        private BoardLayout() {
            pit0 = new Button("0");
            pit1 = new Button("1");
            pit2 = new Button("2");
            pit3 = new Button("3");
            pit4 = new Button("4");
            pit5 = new Button("5");
            pit6 = new Button("6");
            pit7 = new Button("7");
            pit8 = new Button("8");
            pit9 = new Button("9");
            pit10 = new Button("10");
            pit11 = new Button("11");

            pitButtons = new Button[]{pit5, pit4, pit3, pit2, pit1, pit0, pit6, pit7, pit8, pit9, pit10, pit11};
        }

        /**
         * Initializes each component on the BoardLayout
         *
         * @return an initialized BoardLayout Component
         */
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

            kalahaPlayer1 = new Button("0");
            kalahaPlayer1.setHeight("80%");
            kalahaPlayer1.setWidth("30%");
            kalahaPlayer1.setEnabled(false);

            kalahaPlayer2 = new Button("0");
            kalahaPlayer2.setHeight("80%");
            kalahaPlayer2.setWidth("30%");
            kalahaPlayer2.setEnabled(false);

            reset = new Button(BoardStringUtils.RESET.getString());

            for (Button pitButton : pitButtons) {
                pitButton.setHeight("50%");
                pitButton.setId(pitButton.getCaption());
            }

            return this;
        }

        /**
         * Arranges each component on the BoardLayout
         *
         * @return an arranged BoardLayout Component
         */
        public BoardLayout layout() {
            // TODO: Can to with array split & use lengths rather than fixed numbers
            for (int i = 0; i < 6; i++) {
                playerOneSide.addComponent(pitButtons[i]);
                playerOneSide.setComponentAlignment(pitButtons[i], Alignment.BOTTOM_CENTER);
            }

            for (int i = 6; i < pitButtons.length; i++) {
                playerTwoSide.addComponent(pitButtons[i]);
                playerTwoSide.setComponentAlignment(pitButtons[i], Alignment.TOP_CENTER);
            }
            // Add the components vertical layout
            pitsLayout.addComponent(playerOneSide);
            pitsLayout.addComponent(playerTwoSide);
            pitsLayout.addComponent(reset);

            // Set component alignment
            pitsLayout.setComponentAlignment(playerOneSide, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(playerTwoSide, Alignment.BOTTOM_CENTER);
            pitsLayout.setComponentAlignment(reset, Alignment.BOTTOM_CENTER);

            // Set expand ratio
            pitsLayout.setExpandRatio(playerOneSide, 5);
            pitsLayout.setExpandRatio(playerTwoSide, 5);
            pitsLayout.setExpandRatio(reset, 2);

            // Add components to this object
            addComponent(kalahaPlayer1);
            addComponent(pitsLayout);
            addComponent(kalahaPlayer2);

            // Set component alignment
            setComponentAlignment(kalahaPlayer1, Alignment.MIDDLE_RIGHT);
            setComponentAlignment(pitsLayout, Alignment.MIDDLE_RIGHT);
            setComponentAlignment(kalahaPlayer2, Alignment.MIDDLE_LEFT);
            // Set expand ratio
            setExpandRatio(pitsLayout, 10);
            setExpandRatio(kalahaPlayer1, 8);
            setExpandRatio(kalahaPlayer2, 8);

            // update UI to display begin conditions
            updatePitButtons();
            disablePlayer2Pits();
            return this;
        }

        /**
         * Adds {@link ClickListener}s to @{@link Button}s to provide functionality to this component
         *
         * @return a BoardLayout with functional buttons
         */
        public BoardLayout setClickerListeners() {
            for (Button pitButton : pitButtons) {
                pitButton.addClickListener((ClickEvent event) -> {
                    // TODO return value
                    boardService.play(Integer.parseInt(pitButton.getId()));
                    updateUI();
                    if (boardService.isGameEnd()) {
                       // TODO add a popup here boi
                    }
                });
            }

            reset.addClickListener((ClickEvent event) -> {
                boardService.reset();
                updateUI();
            });
            return this;
        }

        /**
         * Acts as a refresh for UI components
         *
         * @return a new view of the board, with updated values
         */
        // TODO fix this ugly code
        public BoardLayout updateUI() {
            switch (boardService.getActivePlayerNumber()) {
                case 1: disablePlayer2Pits(); break;
                case 2: disablePlayer1Pits(); break;
                default: throw new IllegalArgumentException("Player unkown");
            }
            updatePitButtons();
            kalahaPlayer1.setCaption(""+boardService.getKalahaStones(boardService.getPlayer1()));
            kalahaPlayer2.setCaption(""+boardService.getKalahaStones(boardService.getPlayer2()));
            return this;
        }

        /**
         * Update the button captions to reflect it's @{@link Pit} counterpart
         */
        private void updatePitButtons() {
            for (Button pit : pitButtons) {
                int index = Integer.parseInt(pit.getId());
                pit.setCaption(""+boardService.getBoard().getStonesInPit(index));
            }
        }

        /**
         * Disable all the @{@link Button}s on Player-2's side
         * Pit index [6] -> [11]
         */
        private void disablePlayer2Pits() {
            for (Button pit : pitButtons) {
                if (Integer.parseInt(pit.getId()) >= 6) {
                    pit.setEnabled(false);
                } else {
                    pit.setEnabled(true);
                }

            }
        }

        /**
         * Disable all the @{@link Button}s on Player-1's side
         * Pit index [0] -> [5]
         */
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
