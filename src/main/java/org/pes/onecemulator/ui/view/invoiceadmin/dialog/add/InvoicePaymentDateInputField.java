package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.time.LocalDate;

class InvoicePaymentDateInputField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentDateInputField() {
        setCaption("Дата оплаты");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "paymentDate");
    }
}
