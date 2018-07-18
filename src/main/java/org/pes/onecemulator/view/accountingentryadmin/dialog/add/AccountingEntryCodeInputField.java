package org.pes.onecemulator.view.accountingentryadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.AccountingEntryModel;

class AccountingEntryCodeInputField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryCodeInputField() {
        setCaption("Код");
        setSizeFull();
        binder.bind(this, "code");
    }
}
