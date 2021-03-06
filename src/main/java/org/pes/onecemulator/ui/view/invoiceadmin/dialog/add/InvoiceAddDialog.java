package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.ui.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.ui.view.invoiceadmin.root.presenter.IInvoiceAdminPresenter;
import org.pes.onecemulator.ui.view.invoiceadmin.root.view.InvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = InvoiceAddDialog.VIEW_NAME)
public class InvoiceAddDialog extends FormDialog implements View, IInvoiceAddDialog {

    public static final String VIEW_NAME = "InvoiceAddDialog";

    private InvoiceAddForm form;

    private final IInvoiceAdminPresenter presenter;

    @Autowired
    public InvoiceAddDialog(IInvoiceAdminPresenter presenter) {
        super("Добавление счета");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.form = new InvoiceAddForm(presenter.getSourceList());
        setForm(form);
        form.getInvoiceSource().addValueChangeListener(e -> {
            if (e.getValue() != null && !e.getValue().isEmpty()) {
                form.getInvoicePayer().setItems(presenter.getPayerListBySource(e.getValue()));
                form.getInvoicePayer().setReadOnly(false);
            } else {
                form.getInvoicePayer().clear();
                form.getInvoicePayer().setReadOnly(true);
            }
        });
        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> returnInvoiceAdminView());
    }

    @Override
    public boolean hasValidationErrors() {
        return form.hasValidationErrors();
    }

    @Override
    public void showValidationErrorMessages() {
        form.validate();
        setErrorMessageAsHtml(form.errorMessagesAsHtml());
        setVisibleOfErrorDisplay(true);
    }

    @Override
    public void hideErrorMessages() {
        setVisibleOfErrorDisplay(false);
    }

    @Override
    public void returnInvoiceAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(InvoiceAdminView.VIEW_NAME);
    }
}
