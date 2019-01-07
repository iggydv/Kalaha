package com.ignatius.ui.commons;

import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides the RegisterPlayerLayout @{@link Component} to the @{@link KalahaUI}
 * Uses the {@link BoardService} class for all backend functionality
 *
 * Pattern used: Builder pattern
 *
 * @author Ignatius de Villiers
 * @since 14 December 2018
 */
@org.springframework.stereotype.Component
public class KalahaRegisterPlayerLayoutFactory implements UIComponentBuilder {

    private static Logger logger = LoggerFactory.getLogger(KalahaRegisterPlayerLayoutFactory.class);
    BoardService boardService;

    private class RegisterPlayerLayout extends VerticalLayout {
        // ui objects
        private TextField player1TextField;
        private TextField player2TextField;
        private Button lockPlayer1;
        private Button lockPlayer2;
        private Button start;

        /**
         * Initializes each component on the RegisterPlayerLayout
         *
         * @return an initialized RegisterPlayerLayout Component
         */
        public RegisterPlayerLayout init() {
            logger.debug("Initializing register player component");
            StringLengthValidator textFieldValidator = new StringLengthValidator("Name limited to 32 characters");
            textFieldValidator.setMaxLength(32);

            player1TextField = new TextField(BoardStringUtils.PLAYER_1.getString());
            player1TextField.setImmediate(true);
            player1TextField.setRequired(true);
            player1TextField.setWidth("50%");
            player1TextField.setDescription("Player-1 name");
            player1TextField.addValidator(textFieldValidator);

            player2TextField = new TextField(BoardStringUtils.PLAYER_2.getString());
            player2TextField.setImmediate(true);
            player2TextField.setRequired(true);
            player2TextField.setWidth("50%");
            player2TextField.setDescription("Player-2 name");
            player2TextField.addValidator(textFieldValidator);

            lockPlayer1 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer1.setWidth("50%");

            lockPlayer2 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer2.setWidth("50%");

            start = new Button(BoardStringUtils.START_GAME.getString());
            start.setWidth("50%");

            return this;
        }

        /**
         * Arranges each component on the RegisterPlayerLayout
         *
         * @return an arranged RegisterPlayerLayout Component
         */
        public RegisterPlayerLayout layout() {
            logger.debug("Initializing register Player component layout");
            addComponent(player1TextField);
            addComponent(lockPlayer1);
            addComponent(player2TextField);
            addComponent(lockPlayer2);
            addComponent(start);
            setComponentAlignment(player1TextField, Alignment.MIDDLE_CENTER);
            setComponentAlignment(player2TextField, Alignment.MIDDLE_CENTER);
            setComponentAlignment(lockPlayer1, Alignment.TOP_CENTER);
            setComponentAlignment(lockPlayer2, Alignment.TOP_CENTER);
            setComponentAlignment(start, Alignment.MIDDLE_CENTER);

            return this;
        }

        /**
         * Adds {@link Button.ClickListener}s to @{@link Button}s to provide functionality to this component
         *
         * @return a RegisterPlayerLayout with functional buttons
         */
        public RegisterPlayerLayout setClickerListeners() {
            logger.debug("Adding clicker listeners to Player component");
            lockPlayer1.addClickListener((ClickEvent event) -> {
                if (!player1TextField.getValue().equals("")) {
                    logger.debug("Setting player-1 name to {}", player1TextField.getValue());
                    boardService.assignPlayerOne(player1TextField.getValue());
                    lockPlayer1.setEnabled(false);
                } else {
                    logger.warn("Player name can't be left empty");
                }

            });

            lockPlayer2.addClickListener((ClickEvent event) -> {
                if (!player2TextField.getValue().equals("")) {
                    logger.debug("Setting player-1 name to {}", player2TextField.getValue());
                    boardService.assignPlayerTwo(player2TextField.getValue());
                    lockPlayer2.setEnabled(false);
                } else {
                    logger.warn("Player name can't be left empty");
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
    public RegisterPlayerLayout createComponent() {
        return new RegisterPlayerLayout().init().layout().setClickerListeners();
    }

    public RegisterPlayerLayout createComponent(BoardService boardService) {
        this.boardService = boardService;
        return createComponent();
    }
}
