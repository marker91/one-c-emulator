package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoiceExternalIdInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoiceExternalIdInputField() {
        setCaption("Внешний идентификатор");
        setSizeFull();
        binder.bind(this, "externalId");
    }
}
