package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

class PayerIdReadOnlyField extends TextField {

    PayerIdReadOnlyField(final UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
