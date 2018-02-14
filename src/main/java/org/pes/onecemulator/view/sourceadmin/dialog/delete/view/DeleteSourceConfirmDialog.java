package org.pes.onecemulator.view.sourceadmin.dialog.delete.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.delete.presenter.IDeleteSourceConfirmDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.delete.presenter.IDeleteSourceConfirmPresenter;
import org.pes.onecemulator.view.sourceadmin.root.presenter.ISourceAdminView;
import org.pes.onecemulator.view.sourceadmin.root.view.SourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeleteSourceConfirmDialog.VIEW_NAME)
public class DeleteSourceConfirmDialog extends ConfirmDialog implements View, IDeleteSourceConfirmDialog {
    public final static String VIEW_NAME = "DeleteSourceConfirmDialog";

    private final IDeleteSourceConfirmPresenter presenter;

    @Autowired
    public DeleteSourceConfirmDialog(IDeleteSourceConfirmPresenter presenter) {
        super("Вы действительно хотите удалить базу 1С?");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        ISourceAdminView sourceView = (ISourceAdminView) event.getOldView();
        addClickListenerToOkButton(e -> presenter.onClickOkButton(sourceView.allGridSelections()));
        addClickListenerToCancelButton(e -> presenter.onClickCancelButton());
    }

    @Override
    public void returnSourceAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(SourceAdminView.VIEW_NAME);
    }
}