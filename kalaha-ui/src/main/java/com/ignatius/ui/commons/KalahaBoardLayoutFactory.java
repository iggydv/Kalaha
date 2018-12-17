package com.ignatius.ui.commons;

// builder pattern
import com.ignatius.data.objects.Pit;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

    private static Logger logger = LoggerFactory.getLogger(KalahaBoardLayoutFactory.class);
    static BoardService boardService;

    public static class BoardLayout extends HorizontalLayout {

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

        private Label player1Label;
        private Label player2Label;

        private HorizontalLayout playerOneSide;
        private HorizontalLayout playerTwoSide;
        private VerticalLayout pitsLayout;

        public BoardLayout() {
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
            logger.debug("Initializing board component");
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

            player1Label = new Label(BoardStringUtils.PLAYER_1.getString());
            player1Label.setWidth("10%");
            player2Label = new Label(BoardStringUtils.PLAYER_2.getString());
            player2Label.setWidth("10%");

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
            logger.debug("Adding component board component layout");
            for (int i = 0; i < pitButtons.length/2; i++) {
                playerOneSide.addComponent(pitButtons[i]);
                playerOneSide.setComponentAlignment(pitButtons[i], Alignment.BOTTOM_CENTER);
            }

            for (int i = 6; i < pitButtons.length; i++) {
                playerTwoSide.addComponent(pitButtons[i]);
                playerTwoSide.setComponentAlignment(pitButtons[i], Alignment.TOP_CENTER);
            }
            // Add the components vertical layout
            pitsLayout.addComponent(player1Label);
            pitsLayout.addComponent(playerOneSide);
            pitsLayout.addComponent(playerTwoSide);
            pitsLayout.addComponent(player2Label);
            pitsLayout.addComponent(reset);

            // Set component alignment
            pitsLayout.setComponentAlignment(player1Label, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(playerOneSide, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(playerTwoSide, Alignment.BOTTOM_CENTER);
            pitsLayout.setComponentAlignment(player2Label, Alignment.MIDDLE_CENTER);
            pitsLayout.setComponentAlignment(reset, Alignment.BOTTOM_CENTER);

            // Set expand ratio
            pitsLayout.setExpandRatio(player1Label, 2);
            pitsLayout.setExpandRatio(playerOneSide, 5);
            pitsLayout.setExpandRatio(playerTwoSide, 5);
            pitsLayout.setExpandRatio(player2Label, 2);
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
            logger.debug("Adding clicker listeners to board component");
            for (Button pitButton : pitButtons) {
                pitButton.addClickListener((ClickEvent event) -> {
                    boardService.play(Integer.parseInt(pitButton.getId()));
                    updateUI();
                    if (boardService.isGameOver()) {
                        Window winner = createWinnerPopUp(boardService.getBoard().winnigPlayer());
                        UI ancestor = this.getUI();
                        ancestor.addWindow(winner);
                        createNotification(BoardStringUtils.PLEASE_CLICK_RESET.getString());
                    }
                });
            }

            reset.addClickListener((ClickEvent event) -> {
                boardService.reset();
                updateUI();
                createNotification(BoardStringUtils.RESET_BUTTON_CLICKED.getString());
            });
            return this;
        }

        /**
         * Acts as a refresh for UI components
         *
         * @return a new view of the board, with updated values
         */
        public BoardLayout updateUI() {
            logger.info("Updating board component");
            switch (boardService.getActivePlayerEnum()) {
                case PLAYER_1: disablePlayer2Pits(); break;
                case PLAYER_2: disablePlayer1Pits(); break;
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
            logger.debug("Updating pit button captions");
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
            logger.debug("Disabling player-2's pits");
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
            logger.debug("Disabling player-1's pits");
            for (Button pit : pitButtons) {
                if (Integer.parseInt(pit.getId()) < 6) {
                    pit.setEnabled(false);
                } else {
                    pit.setEnabled(true);
                }
            }
        }


        /**
         * Creates a popup window that displays the winning @{@link com.ignatius.data.objects.Player}'s name
         * @param winner is the @{@link BoardStringUtils} enum received from the @{@link BoardService}
         *               PLAYER 1:  Player-1 is the winner
         *               PLAYER 2:  Player-2 is the winner
         *               TIE:       The game is tied
         */
        public Window createWinnerPopUp(BoardStringUtils winner) {
            logger.debug("Creating popup winner popup window");
            VerticalLayout subWindowContent = new VerticalLayout();
            String caption;

            switch (winner) {
                case PLAYER_1: caption = boardService.getPlayer1().getPlayerName(); break;
                case PLAYER_2: caption = boardService.getPlayer2().getPlayerName(); break;
                case TIE: caption = BoardStringUtils.TIE.getString(); break;
                default: {
                    logger.error("Invalid enum received");
                    throw new IllegalArgumentException("Invalid enum received");}
            }
            Label winnerCaption = new Label(caption);
            winnerCaption.setWidth("100px");
            Image winnerImage = createImage("/kalaha-ui/src/main/resources/winner.png");

            subWindowContent.addComponent(winnerCaption);
            subWindowContent.addComponent(winnerImage);

            subWindowContent.setComponentAlignment(winnerCaption, Alignment.BOTTOM_CENTER);
            subWindowContent.setComponentAlignment(winnerImage, Alignment.MIDDLE_CENTER);

            Window subWindow = new Window(BoardStringUtils.WINNER.getString());
            subWindow.setContent(subWindowContent);
            subWindow.setModal(true);
            subWindow.setDraggable(false);
            subWindow.setHeight("500px");
            subWindow.setWidth("500px");
            subWindow.center();
            return subWindow;
        }

        /**
         * Creates a @{@link Notification} on the UI, indicating that the reset button needs to be clicked
         * to reset the game.
         */
        private void createNotification(String message) {
            Notification notification = new Notification("INFO", message);
            notification.setDelayMsec(10000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        }

        /**
         * @param imagePath where the logo can be found
         * @return an @{@link Image} that can be used on a UI
         */
        private Image createImage(String imagePath) {
            logger.debug("Creating winner image");
            String basePath = System.getProperty("user.dir");
            FileResource resource = new FileResource(new File(basePath + imagePath));
            return new Image("", resource);
        }
    }

    @Override
    public BoardLayout createComponent() {
        return new BoardLayout().init().layout().setClickerListeners();
    }

    public BoardLayout createComponent(BoardService boardService) {
        this.boardService = boardService;
        return createComponent();
    }
}
