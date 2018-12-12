package com.ignatius.ui.commons;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Kalaha;
import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path=KalahaUI.NAME)
@SuppressWarnings("serial")
@Title("K a l a h a")
@Theme("valo")
public class KalahaUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(KalahaUI.class);
    static final String NAME = "kalaha";
    private VerticalLayout rootLayout = new VerticalLayout();
    private BoardService boardService = new BoardService();

    @Autowired
    private KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

    @Autowired
    private KalahaLogoLayoutFactory kalahaLogoLayoutFactory;

    @Autowired
    private KalahaRegisterPlayerLayoutFactory kalahaRegisterPlayerLayoutFactory;

//    private Button quit;
//    private Button reset;
//    private Button rules;

    protected void init(VaadinRequest vaadinRequest) {
        logger.info("Initializing Components");
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        createPlayerRegistrationWindow();

        Panel logoPanel = new Panel();
        logoPanel.setWidth("100%");
        logoPanel.setHeight("100%");

        Panel gamePanel = new Panel();
        gamePanel.setWidth("100%");
        gamePanel.setHeight("100%");

        logger.info("Creating Logo");
        Component logo = kalahaLogoLayoutFactory.createComponent();
        logo.setSizeFull();
        logoPanel.setContent(logo);

        logger.info("Creating Board");
        Component board = kalahaBoardLayoutFactory.createComponent(boardService);
        board.setSizeFull();
        gamePanel.setContent(board);

        rootLayout.addComponent(logoPanel);
        rootLayout.addComponent(gamePanel);
        rootLayout.setExpandRatio(gamePanel, 5);
        rootLayout.setExpandRatio(logoPanel, 2);
        rootLayout.setComponentAlignment(gamePanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(logoPanel, Alignment.MIDDLE_CENTER);

        setContent(rootLayout);
    }

    private void createPlayerRegistrationWindow() {
        logger.info("Creating Player Registration Window");
        Window subWindow = new Window(BoardStringUtils.REGISTER_PLAYER_NAMES.getString());
        Component registerPlayersLayout = kalahaRegisterPlayerLayoutFactory.createComponent(boardService);
        registerPlayersLayout.setSizeFull();
        subWindow.setContent(registerPlayersLayout);
        subWindow.setModal(true);
        subWindow.setClosable(false);
        subWindow.setDraggable(false);
        subWindow.setHeight("500px");
        subWindow.setWidth("500px");
        subWindow.center();
        addWindow(subWindow);
    }
}
