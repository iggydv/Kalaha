package com.ignatius.ui.commons;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(PlayerTextFieldExtension.class)
public class PlayerTextFieldExtensionConnector extends AbstractExtensionConnector {

    @Override
    protected void extend(ServerConnector target) {

        final Widget widget = ((ComponentConnector) target).getWidget();

        widget.addDomHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if (isEnabled() && isEnterKey(event)) {
                    getRpcProxy(PlayerTextFieldExtensionServerRpc.class).enterKeyPressed();
                }
            }
        }, KeyPressEvent.getType());

    }

    private boolean isEnterKey(KeyPressEvent event) {
        return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER;
    }

}
