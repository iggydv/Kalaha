package com.ignatius.ui.commons;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import java.io.File;

/**
 * Provides a LogoLayout @{@link Component} for the @{@link KalahaUI}
 *
 * Pattern used: Builder pattern
 *
 * @author Ignatius de Villiers
 * @since 14 Dec 2018
 */
@org.springframework.stereotype.Component
public class KalahaLogoLayoutFactory implements UIComponentBuilder {

    private class LogoLayout extends HorizontalLayout {
        private Image logo;

        /**
         * Initializes each component on the LogoLayout
         *
         * @return an initialized LogoLayout Component
         */
        public LogoLayout init() {
            logo = createImage("/kalaha-ui/src/main/resources/kalaha-logo-2.png");
            return this;
        }

        /**
         * Arranges each component on the LogoLayout
         *
         * @return an arranged LogoLayout Component
         */
        public LogoLayout layout() {
            addComponent(logo);
            setComponentAlignment(logo, Alignment.TOP_CENTER);
            return this;
        }

        /**
         * @param imagePath where the logo can be found
         * @return an @{@link Image} that can be used on a UI
         */
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
