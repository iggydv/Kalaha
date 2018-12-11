package com.ignatius.ui.commons;

import com.ignatius.utils.BoardStringUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;

@SpringUI(path = KalahaUI.NAME)
@Title("K a l a h a")
@Theme("valo")
public class KalahaUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(KalahaUI.class);
    static final String NAME = "/ui";
    private VerticalLayout rootLayout = new VerticalLayout();

    @Autowired
    private KalahaBoardLayoutFactory kalahaBoardLayoutFactory;

//    private Button kalahaPlayer1;
//    private Button kalahaPlayer2;
//    private Button pit0;
//    private Button pit1;
//    private Button pit2;
//    private Button pit3;
//    private Button pit4;
//    private Button pit5;
//    private Button pit6;
//    private Button pit7;
//    private Button pit8;
//    private Button pit9;
//    private Button pit10;
//    private Button pit11;
//    private Button quit;
//    private Button reset;
//    private Button rules;

    protected void init(VaadinRequest vaadinRequest) {
        logger.info("Initializing UI");
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        Panel gamePanel = new Panel();
        gamePanel.setWidth("100%");
        gamePanel.setHeight("100%");

        createLogoPanel();
        logger.info("Creating game panel");
        Component board = kalahaBoardLayoutFactory.createComponent();
        board.setSizeFull();
        gamePanel.setContent(board);
        rootLayout.addComponent(gamePanel);
        rootLayout.setExpandRatio(gamePanel, 5);
        rootLayout.setComponentAlignment(gamePanel, Alignment.MIDDLE_CENTER);

        setContent(rootLayout);
    }

    private Image createImage(String imagePath) {
        String basePath = System.getProperty("user.dir");
        FileResource resource = new FileResource(new File(basePath + imagePath));
        return new Image("", resource);
    }

    private void createLogoPanel() {
        logger.info("Creating logo panel");
        Panel logoPanel = new Panel();
        logoPanel.setWidth("100%");
        logoPanel.setHeight("100%");

        HorizontalLayout uiLayout = new HorizontalLayout();
        uiLayout.setSizeFull();
        uiLayout.setMargin(true);

        Image image = createImage("/kalaha-ui/src/main/resources/kalaha-logo-2.png");
        uiLayout.addComponent(image);
        uiLayout.setComponentAlignment(image, Alignment.TOP_CENTER);
        logoPanel.setContent(uiLayout);
        rootLayout.addComponent(logoPanel);
        rootLayout.setExpandRatio(logoPanel, 2);
        rootLayout.setComponentAlignment(logoPanel, Alignment.MIDDLE_CENTER);
    }


}
