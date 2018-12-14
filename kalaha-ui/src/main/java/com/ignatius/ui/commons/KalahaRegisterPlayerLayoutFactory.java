package com.ignatius.ui.commons;

import com.ignatius.data.objects.Player;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.ValidationException;
//import com.vaadin.data.fieldgroup.BeanFieldGroup;
//import com.vaadin.data.fieldgroup.FieldGroup;
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

        Binder<Player> fieldGroupP1 = new Binder<>(Player.class);
        Binder<Player> fieldGroupP2 = new Binder<>(Player.class);

        public RegisterPlayerLayout init() {
            player1 = new TextField(BoardStringUtils.PLAYER_1.getString(), event -> event.getValue());
            player1.setPlaceholder("Ma se");
            //PlayerTextFieldExtension extension = new PlayerTextFieldExtension(player1);
            player1.setWidth("50%");
            player2 = new TextField(BoardStringUtils.PLAYER_2.getString());
            player2.setWidth("50%");
            lockPlayer1 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer1.setWidth("50%");
            lockPlayer2 = new Button(BoardStringUtils.LOCK.getString());
            lockPlayer2.setWidth("50%");
            start = new Button(BoardStringUtils.START_GAME.getString());
            start.setWidth("50%");

            fieldGroupP1.forField(player1).withNullRepresentation("").bind(Player::getPlayerName, Player::setPlayerName);
            fieldGroupP1.bindInstanceFields(this);
            fieldGroupP1.setBean(boardService.getPlayer1());


            fieldGroupP2.forField(player2).bind(Player::getPlayerName, Player::setPlayerName);
            fieldGroupP2.bindInstanceFields(this);
            fieldGroupP2.setBean(boardService.getPlayer1());

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
            //player1.addValueChangeListener((ValueChangeListener<String>) event -> System.out.println("value changed..."));

            player1.setValue("iggy-pop");
            player2.setValue("clara-pop");

            lockPlayer1.addClickListener((ClickEvent event) -> {
                //lockPlayer1.setCaption(boardService.getPlayer1().getPlayerName());
                System.out.println("text: " + player1.getValue());
                if (!player1.isEmpty()) {
                    System.out.println("l");
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
                System.out.println("text: " + player2.getValue());
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
