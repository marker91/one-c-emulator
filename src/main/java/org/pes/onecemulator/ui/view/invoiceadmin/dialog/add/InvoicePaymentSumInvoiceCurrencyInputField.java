package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePaymentSumInvoiceCurrencyInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumInvoiceCurrencyInputField() {
        setCaption("Сумма оплаты в валюте счёта");
        setSizeFull();
        binder.bind(this, "paymentSumInvoiceCurrency");
    }
}
