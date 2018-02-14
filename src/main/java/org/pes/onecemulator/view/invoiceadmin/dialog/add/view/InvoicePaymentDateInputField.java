package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoicePaymentDateInputField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentDateInputField() {
        setCaption("Дата оплаты");
        setSizeFull();
        binder.bind(this, "paymentDate");
    }
}