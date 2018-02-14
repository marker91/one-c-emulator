package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoicePaymentSumInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumInputField() {
        setCaption("Сумма оплты");
        setSizeFull();
        binder.bind(this, "paymentSum");
    }
}