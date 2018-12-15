package com.ignatius.ui.commons;

import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = KalahaUI.NAME)
@SuppressWarnings("serial")
@Title("K a l a h a")
@Theme("valo")
public class KalahaUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(KalahaUI.class);
    static final String NAME = "kalaha";
    private VerticalLayout rootLayout = new VerticalLayout();
    private BoardService boardService = new BoardService();

    // Custom UI components

    @Autowired
    private KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

    @Autowired
    private KalahaLogoLayoutFactory kalahaLogoLayoutFactory;

    @Autowired
    private KalahaRegisterPlayerLayoutFactory kalahaRegisterPlayerLayoutFactory;

    @Autowired
    private KalahaSettingsLayoutFactory kalahaSettingsLayoutFactory;

    /**
     * Initialize all the required UI {@link Component}s
     *
     * @param vaadinRequest
     */
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

        Panel settingsPanel = new Panel();
        settingsPanel.setWidth("100%");
        settingsPanel.setHeight("100%");

        logger.info("Creating Logo");
        Component logo = kalahaLogoLayoutFactory.createComponent();
        logo.setSizeFull();
        logoPanel.setContent(logo);

        logger.info("Creating Board");
        Component board = kalahaBoardLayoutFactory.createComponent(boardService);
        board.setSizeFull();
        gamePanel.setContent(board);

        logger.info("Creating Settings");
        Component settings = kalahaSettingsLayoutFactory.createComponent();
        settings.setSizeFull();
        settingsPanel.setContent(settings);

        rootLayout.addComponent(logoPanel);
        rootLayout.addComponent(gamePanel);
        rootLayout.addComponent(settingsPanel);

        rootLayout.setExpandRatio(gamePanel, 5);
        rootLayout.setExpandRatio(logoPanel, 2);
        rootLayout.setExpandRatio(settingsPanel, 1);

        rootLayout.setComponentAlignment(gamePanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(logoPanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(settingsPanel, Alignment.MIDDLE_CENTER);

        setContent(rootLayout);
    }

    /**
     * Creates an initial window to allow for player initialization
     */
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
