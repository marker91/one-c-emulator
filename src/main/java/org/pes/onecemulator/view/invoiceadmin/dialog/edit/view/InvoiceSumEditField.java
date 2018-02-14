package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

import java.math.BigDecimal;

public class InvoiceSumEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSumEditField(String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

    BigDecimal valueAsBigDecimal() {
        return new BigDecimal(getValue());
    }
}