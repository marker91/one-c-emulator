package org.pes.onecemulator.view.sourceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.SourceModel;

class SourceNameInputField extends TextField {

    final BeanValidationBinder<SourceModel> binder = new BeanValidationBinder<>(SourceModel.class);

    SourceNameInputField() {
        setCaption("Название");
        setSizeFull();
        binder.bind(this, "name");
    }
}
