package com.ignatius.ui.commons;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Provides a LogoLayout @{@link Component} for the @{@link KalahaUI}
 *
 * Pattern used: Builder pattern
 *
 * @author Ignatius de Villiers
 * @since 14 December 2018
 */
@org.springframework.stereotype.Component
public class KalahaLogoLayoutFactory implements UIComponentBuilder {

    private static Logger logger = LoggerFactory.getLogger(KalahaLogoLayoutFactory.class);

    private class LogoLayout extends HorizontalLayout {
        private Image logo;

        /**
         * Initializes each component on the LogoLayout
         *
         * @return an initialized LogoLayout Component
         */
        public LogoLayout init() {
            logger.debug("Initializing logo component");
            logo = createImage("/kalaha-ui/src/main/resources/kalaha-logo.png");
            return this;
        }

        /**
         * Arranges each component on the LogoLayout
         *
         * @return an arranged LogoLayout Component
         */
        public LogoLayout layout() {
            logger.debug("Adding component logo layout");
            addComponent(logo);
            setComponentAlignment(logo, Alignment.TOP_CENTER);
            return this;
        }

        /**
         * @param imagePath where the logo can be found
         * @return an @{@link Image} that can be used on a UI
         */
        private Image createImage(String imagePath) {
            logger.debug("Creating logo component image");
            String basePath = System.getProperty("user.dir");
            FileResource resource = new FileResource(new File(basePath + imagePath));
            return new Image("", resource);
        }
    }

    @Override
    public LogoLayout createComponent() {
        return new LogoLayout().init().layout();
    }
}
