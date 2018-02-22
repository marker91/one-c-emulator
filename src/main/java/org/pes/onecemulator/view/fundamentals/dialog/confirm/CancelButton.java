package org.pes.onecemulator.view.fundamentals.dialog.confirm;

import com.vaadin.ui.Button;

class CancelButton extends Button {

    private static final String ID = "CancelButton";

    CancelButton() {
        setId(ID);
        setCaption("Отмена");
        setWidth(120, Unit.PIXELS);
    }
}
