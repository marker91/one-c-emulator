package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.ArrayList;
import java.util.List;

class PayerEditForm extends FormLayout {

    private final PayerIdReadOnlyField payerId;

    private final PayerCodeEditField payerCode;

    private final PayerNameEditField payerName;

    private final PayerFullNameEditField payerFullName;

    private final PayerInnEditField payerInn;

    private final PayerKppEditField payerKpp;

    private final PayerAddressEditField payerAddress;

    private final PayerSubcontractorEditField payerSubcontractor;

    private final PayerCustomerEditField payerCustomer;

    private final PayerSourceEditField payerSource;

    PayerEditForm(PayerModel target, List<String> sourceList) {
        this.payerId = new PayerIdReadOnlyField(target.getId());
        this.payerCode = new PayerCodeEditField(target.getCode());
        this.payerName = new PayerNameEditField(target.getName());
        this.payerFullName = new PayerFullNameEditField(target.getFullName());
        this.payerInn = new PayerInnEditField(target.getInn());
        this.payerKpp = new PayerKppEditField(target.getKpp());
        this.payerAddress = new PayerAddressEditField(target.getAddress());
        this.payerSubcontractor = new PayerSubcontractorEditField(target.isSubcontractor());
        this.payerCustomer = new PayerCustomerEditField(target.isCustomer());
        this.payerSource = new PayerSourceEditField(new ArrayList<>(target.getSources()), sourceList);

        addComponents(payerId, payerCode, payerName, payerFullName, payerInn, payerKpp, payerAddress, payerSubcontractor, payerCustomer, payerSource);
        setMargin(false);
    }

    void validate() {
        payerCode.binder.validate();
        payerName.binder.validate();
        payerFullName.binder.validate();
        payerInn.binder.validate();
        payerKpp.binder.validate();
        payerAddress.binder.validate();
        payerSubcontractor.binder.validate();
        payerCustomer.binder.validate();
        payerSource.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return payerCode.binder.isValid()
                && payerName.binder.isValid()
                && payerFullName.binder.isValid()
                && payerInn.binder.isValid()
                && payerKpp.binder.isValid()
                && payerAddress.binder.isValid()
                && payerSubcontractor.binder.isValid()
                && payerCustomer.binder.isValid()
                && payerSource.binder.isValid();
    }

    boolean hasChanges() {
        // note: binder.setBean() and binder.hasChange() are not work I expected
        return payerCode.hasChanges()
                || payerName.hasChanges()
                || payerFullName.hasChanges()
                || payerInn.hasChanges()
                || payerKpp.hasChanges()
                || payerAddress.hasChanges()
                || payerSubcontractor.hasChanges()
                || payerCustomer.hasChanges()
                || payerSource.hasChanges();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                payerCode.getErrorMessage(),
                payerName.getErrorMessage(),
                payerFullName.getErrorMessage(),
                payerInn.getErrorMessage(),
                payerKpp.getErrorMessage(),
                payerAddress.getErrorMessage(),
                payerSubcontractor.getErrorMessage(),
                payerCustomer.getErrorMessage(),
                payerSource.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    PayerModel valueAsObject() {
        final PayerModel object = new PayerModel();
        object.setId(payerId.valueAsUUID());
        object.setCode(payerCode.getValue());
        object.setName(payerName.getValue());
        object.setFullName(payerFullName.getValue());
        object.setInn(payerInn.getValue());
        object.setKpp(payerKpp.getValue());
        object.setAddress(payerAddress.getValue());
        object.setSubcontractor(payerSubcontractor.getValue());
        object.setCustomer(payerCustomer.getValue());
        object.getSources().addAll(payerSource.getValue());

        return object;
    }
}
