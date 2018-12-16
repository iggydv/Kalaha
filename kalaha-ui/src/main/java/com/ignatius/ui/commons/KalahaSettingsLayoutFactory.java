package com.ignatius.ui.commons;

import com.ignatius.service.board.BoardService;
import com.ignatius.utils.BoardStringUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Provides the SettingsLayout @{@link Component} to the @{@link KalahaUI}
 * Uses the {@link BoardService} class for all backend functionality
 *
 * @author Ignatius de Villiers
 * @since 14 Dec 2018
 */
@org.springframework.stereotype.Component
public class KalahaSettingsLayoutFactory implements UIComponentBuilder {

    private static Logger logger = LoggerFactory.getLogger(KalahaSettingsLayoutFactory.class);

    private class SettingsLayout extends HorizontalLayout {

        private Button quit;
        private PopupView help;

        /**
         * Initializes each component on the SettingsLayout
         *
         * @return an initialized SettingsLayout Component
         */
        public SettingsLayout init() {
            logger.debug("Initializing settings component");
            quit = new Button(BoardStringUtils.QUIT.getString());
            quit.setWidth("100px");
            help = createPopUpView();
            return this;
        }

        /**
         * Arranges each component on the SettingsLayout
         *
         * @return an arranged SettingsLayout Component
         */
        public SettingsLayout layout() {
            logger.debug("Adding settings layout");
            addComponent(quit);
            addComponent(help);
            setComponentAlignment(quit, Alignment.MIDDLE_CENTER);
            setComponentAlignment(help, Alignment.MIDDLE_CENTER);
            return this;
        }

        /**
         * Adds {@link Button.ClickListener}s to @{@link Button}s to provide functionality to this component
         *
         * @return a SettingsLayout with functional buttons
         */
        public SettingsLayout setClickerListeners() {
            logger.debug("Adding clicker listeners to settings component");
            quit.addClickListener((Button.ClickEvent event) -> {
                final String uri = "http://localhost:8080/quit";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForObject(uri, String.class);
            });

            return this;
        }

        /**
         * Create a popup view containing stating Game Rules. These rules are acquired through a REST API call to:
         * http://localhost:8080/help
         *
         * @return a @{@link PopupView} that can be added to a component
         */
        private PopupView createPopUpView() {
            logger.debug("Creating popup view");
            final String uri = "http://localhost:8080/help";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            TextArea helpText = new TextArea();
            helpText.setValue(result);
            helpText.setHeight("500px");
            helpText.setWidth("500px");
            PopupView popup = new PopupView("Rules", helpText);

            return popup;
        }
    }

    @Override
    public Component createComponent() {
        return new SettingsLayout().init().layout().setClickerListeners();
    }
}

