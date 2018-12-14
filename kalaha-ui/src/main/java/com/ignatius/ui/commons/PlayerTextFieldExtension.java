package com.ignatius.ui.commons;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

public class PlayerTextFieldExtension extends AbstractExtension {

    public PlayerTextFieldExtension(TextField field) {
        super.extend(field);
        PlayerTextFieldExtensionServerRpc rpc = this::handleEnterKeyPressed;
        registerRpc(rpc);
    }

    private void handleEnterKeyPressed() {
        System.out.println("ENTER KEY PRESSED");
    }
}
