package org.pes.onecemulator.view.sourceadmin.dialog.add.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.add.presenter.ISourceAddDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.add.presenter.ISourceAddPresenter;
import org.pes.onecemulator.view.sourceadmin.root.view.SourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = SourceAddDialog.VIEW_NAME)
public class SourceAddDialog extends FormDialog implements View, ISourceAddDialog {

    public static final String VIEW_NAME = "SourceAddDialog";

    private SourceAddForm form;

    private final ISourceAddPresenter presenter;

    @Autowired
    public SourceAddDialog(ISourceAddPresenter presenter) {
        super("Добавление базы 1С");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.form = new SourceAddForm();
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> presenter.onClickCancelButton());
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
    public void returnSourceAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(SourceAdminView.VIEW_NAME);
    }
}