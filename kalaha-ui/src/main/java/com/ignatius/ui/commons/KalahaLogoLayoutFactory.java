package com.ignatius.ui.commons;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;

import java.io.File;

@org.springframework.stereotype.Component
public class KalahaLogoLayoutFactory implements UIComponentBuilder {

    private class LogoLayout extends HorizontalLayout {
        private Image logo;

        public LogoLayout init() {

            logo = createImage("/kalaha-ui/src/main/resources/kalaha-logo-2.png");

            return this;
        }

        public LogoLayout layout() {
            addComponent(logo);
            setComponentAlignment(logo, Alignment.TOP_CENTER);
            return this;
        }

        private Image createImage(String imagePath) {
            String basePath = System.getProperty("user.dir");
            FileResource resource = new FileResource(new File(basePath + imagePath));
            return new Image("", resource);
        }
    }
    @Override
    public Component createComponent() {
        return new LogoLayout().init().layout();
    }
}
