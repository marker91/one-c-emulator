package org.pes.onecemulator.view.accountingentryadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

class AccountingEntryExpenseNumberEditField extends ComboBox<String> {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntryExpenseNumberEditField(String origin, List<String> expenseRequests) {
        this.origin = origin;
        setCaption("Номер заявки на расход");
        setEmptySelectionAllowed(false);
        setItems(expenseRequests);
        setValue(origin);
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        binder.bind(this, "expenseNumber");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
