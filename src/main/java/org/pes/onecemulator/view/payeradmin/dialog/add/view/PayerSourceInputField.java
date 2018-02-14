package org.pes.onecemulator.view.payeradmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBoxGroup;
import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public class PayerSourceInputField extends CheckBoxGroup<String> {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerSourceInputField(List<String> origin) {
        super("БД 1С");
        setItems(origin);
        setSizeFull();
        binder.bind(this, "sources");
    }
}