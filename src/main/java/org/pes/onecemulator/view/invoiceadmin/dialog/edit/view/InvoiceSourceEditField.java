package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoiceSourceEditField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    private final List<String> sources;

    InvoiceSourceEditField(String source, List<String> sources) {
        this.origin = source;
        this.sources = sources;
        setItems(sources);
        setValue(origin);
        setCaption("БД 1С");
        setSizeFull();
        binder.bind(this, "source");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}