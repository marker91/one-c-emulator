package org.pes.onecemulator.view.sourceadmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.view.fundamentals.layout.BaseViewLayout;
import org.pes.onecemulator.view.sourceadmin.dialog.delete.view.DeleteSourceConfirmDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.add.view.SourceAddDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.edit.view.SourceEditDialog;
import org.pes.onecemulator.view.sourceadmin.root.presenter.ISourceAdminPresenter;
import org.pes.onecemulator.view.sourceadmin.root.presenter.ISourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = SourceAdminView.VIEW_NAME)
public class SourceAdminView extends BaseViewLayout implements View, ISourceAdminView {

    public static final String VIEW_NAME = "SourceAdminView";

    public static final String CAPTION = "Управление базами 1С";

    public static final String TITLE = "1C-emulator: " + CAPTION;

    private final ViewHeader viewHeader = new ViewHeader(CAPTION);

    private final SourceAdminViewBody viewBody = new SourceAdminViewBody();

    private final ISourceAdminPresenter presenter;

    @Autowired
    public SourceAdminView(ISourceAdminPresenter presenter) {
        // 1st construction
        super();
        this.presenter = presenter;
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
        addHeaderAndBody(viewHeader, viewBody);
    }

    @PostConstruct
    void init() {
        // 2nd construction
        presenter.attachView(this);
        presenter.loadSources();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.sourceGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<SourceModel> sourceModelList) {
        viewBody.sourceGrid.binding(sourceModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.sourceGrid.filterBy(viewBody.controlArea.searchText());
    }

    @Override
    public void toStateOfOnlyCanAdd() {
        viewBody.controlArea.toStateOfOnlyCanAdd();
    }

    @Override
    public void toStateOfCanAll() {
        viewBody.controlArea.toStateOfCanAll();
    }

    @Override
    public void toStateOfCanAddAndDelete() {
        viewBody.controlArea.toStateOfCanAddAndDelete();
    }

    @Override
    public List<SourceModel> allGridSelections() {
        return viewBody.sourceGrid.allSelections();
    }

    @Override
    public SourceModel gridSelection() {
        return viewBody.sourceGrid.selection();
    }

    @Override
    public void launchPayerAddDialog() {
        getUI().getNavigator().navigateTo(SourceAddDialog.VIEW_NAME);
    }

    @Override
    public void launchPayerEditDialog() {
        getUI().getNavigator().navigateTo(SourceEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteConfirmDialog() {
        getUI().getNavigator().navigateTo(DeleteSourceConfirmDialog.VIEW_NAME);
    }
}
