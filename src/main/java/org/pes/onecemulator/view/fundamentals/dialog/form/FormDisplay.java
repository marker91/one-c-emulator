package org.pes.onecemulator.view.fundamentals.dialog.form;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

class FormDisplay extends Panel {

    FormDisplay() {
        setStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    void setFrom(FormLayout form) {
        setContent(form);
    }
}
