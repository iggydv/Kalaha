package com.ignatius.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = KalahaUI.NAME)
@Title("K a l a h a")
@Theme("valo")
public class KalahaUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(KalahaUI.class);
    static final String NAME = "/ui";
    private VerticalLayout rootLayout = new VerticalLayout();

    @Autowired
    private KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

    @Autowired
    private KalahaLogoLayoutFactory kalahaLogoLayoutFactory;

//    private Button quit;
//    private Button reset;
//    private Button rules;

    protected void init(VaadinRequest vaadinRequest) {
        logger.info("Initializing UI");
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

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
        Component board = kalahaBoardLayoutFactory.createComponent();
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
}
