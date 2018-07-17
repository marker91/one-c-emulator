package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

class ExpenseRequestIdReadOnlyField extends TextField {

    ExpenseRequestIdReadOnlyField(UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
