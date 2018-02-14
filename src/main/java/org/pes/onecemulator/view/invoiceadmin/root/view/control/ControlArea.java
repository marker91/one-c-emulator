package org.pes.onecemulator.view.invoiceadmin.root.view.control;

import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class ControlArea extends HorizontalLayout {

    private final SearchField searchField = new SearchField();

    private final AddEditDeleteButtons addEditDeleteButtons = new AddEditDeleteButtons();

    public ControlArea() {
        addComponents(searchField, addEditDeleteButtons);
    }

    public String searchText() {
        return searchField.searchText.getValue();
    }

    public void addClickEventListenerToSearchButton(ClickListener clickListener) {
        searchField.searchButton.addClickListener(clickListener);
    }

    public void addClickEventListenerToAddButton(ClickListener clickListener) {
        addEditDeleteButtons.addButton.addClickListener(clickListener);
    }

    public void addClickEventListenerToEditButton(ClickListener clickListener) {
        addEditDeleteButtons.editButton.addClickListener(clickListener);
    }

    public void addClickEventListenerToDeleteButton(ClickListener clickListener) {
        addEditDeleteButtons.deleteButton.addClickListener(clickListener);
    }

    public void toStateOfOnlyCanAdd() {
        addEditDeleteButtons.addButton.setEnabled(true);
        addEditDeleteButtons.editButton.setEnabled(false);
        addEditDeleteButtons.deleteButton.setEnabled(false);
    }

    public void toStateOfCanAll() {
        addEditDeleteButtons.addButton.setEnabled(true);
        addEditDeleteButtons.editButton.setEnabled(true);
        addEditDeleteButtons.deleteButton.setEnabled(true);
    }

    public void toStateOfCanAddAndDelete() {
        addEditDeleteButtons.addButton.setEnabled(true);
        addEditDeleteButtons.editButton.setEnabled(false);
        addEditDeleteButtons.deleteButton.setEnabled(true);
    }
}
