package org.pes.onecemulator.ui.view.fundamentals.control;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

class DeleteButton extends Button {

    private static final String ID = "DeleteButton";

    DeleteButton() {
        setId(ID);
        setIcon(VaadinIcons.TRASH, "Удалить");
        addStyleName(ValoTheme.BUTTON_DANGER);
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    }
}
