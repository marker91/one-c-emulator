package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.event.ui.UINotificationEvent;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.httpclient.ExpenseRequestHttp;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.EmployeeRepository;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    @Value("${crm.interaction.host:#{null}}")
    private String crmHost;

    @Value("${crm.interaction.uri:#{null}}")
    private String crmUri;

    @Value("${crm.interaction.token:#{null}}")
    private String crmToken;

    private final InvoiceRepository invoiceRepository;

    private final SourceRepository sourceRepository;

    private final EmployeeRepository employeeRepository;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public CrmInteractionServiceImpl(InvoiceRepository invoiceRepository, SourceRepository sourceRepository, EmployeeRepository employeeRepository, ApplicationEventPublisher publisher) {
        this.invoiceRepository = invoiceRepository;
        this.sourceRepository = sourceRepository;
        this.employeeRepository = employeeRepository;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public List<DocumentCrm> getDocumentsCrmById(final List<DocumentCrm> documentCrmList, final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm ->
                        invoiceRepository.findByIdAndSource(
                                documentCrm.getId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(this::getDocumentCrm)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<DocumentCrm> getDocumentsCrmByExternalId(final List<DocumentCrm> documentCrmList, final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm ->
                        invoiceRepository.findByExternalIdAndSource(
                                documentCrm.getExternalId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(this::getDocumentCrm)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<PayerCrm> getAllPayersCrmBySource(final String sourceName) {
        return sourceRepository.findByName(sourceName)
                .map(Source::getPayers)
                .orElse(new HashSet<>())
                .stream()
                .map(this::getPayerCrm)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<EmployeeCrm> getAllEmployeesCrmBySourceAndExternalIds(final String sourceName, final List<String> externalIds) {
        return externalIds.stream()
                .map(employeeRepository::findByExternalId)
                .map(oe -> oe.orElse(null))
                .filter(Objects::nonNull)
                .filter(e -> e.getSources().stream().anyMatch(s -> s.getName().equals(sourceName)))
                .map(this::getEmployeeCrm)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<EmployeeCrm> getAllEmployeesCrm() {
        return employeeRepository.findAll().stream().map(this::getEmployeeCrm).collect(Collectors.toList());
    }

    @Async
    @Override
    public void sendAccountingEntryToCrm(final AccountingEntry accountingEntry) {
        try {
            final ExpenseRequestHttp expenseRequestHttp =
                    new ExpenseRequestHttp(accountingEntry, crmHost, crmUri, crmToken);
            postExpenseRequestInfoToUI(expenseRequestHttp.call());
        } catch (Exception e) {
            postExpenseRequestErrorToUI(e);
        }
    }

    private void postExpenseRequestInfoToUI(String uri) {
        publisher.publishEvent(new UINotificationEvent(this, "Запрос: " + uri + " отправлен в CRM.", false));
    }

    private void postExpenseRequestErrorToUI(Exception e) {
        publisher.publishEvent(new UINotificationEvent(this, "Ошибка отправки запроса в CRM: " + e.getMessage(), true));
    }

    private DocumentCrm getDocumentCrm(final Invoice entity) {
        final DocumentCrm model = new DocumentCrm();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerName(entity.getPayer() != null ? entity.getPayer().getName() : null);
        model.setInvoiceSum(entity.getSum());
        model.setDate(entity.getDate());
        model.setStatus(entity.getStatus());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumRUB(entity.getPaymentSumRUB());
        model.setExternalId(entity.getExternalId());
        model.setInvoiceSumRUB(entity.getSumRUB());
        model.setInvoiceCurrency(entity.getCurrency());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setPaymentSum(entity.getPaymentSum());

        return model;
    }

    private PayerCrm getPayerCrm(final Payer entity) {
        final PayerCrm model = new PayerCrm();
        model.setId(entity.getAddress());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());

        return model;
    }

    private EmployeeCrm getEmployeeCrm(final Employee entity) {
        final EmployeeCrm model = new EmployeeCrm();
        model.setExternalId(entity.getExternalId());
        model.setFullName(entity.getFullName());
        model.setGender(entity.getGender());
        model.setBirthday(entity.getBirthday());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setFizId(entity.getFizId());
        model.setPosition(entity.getPosition());
        model.setUnit(entity.getUnit());
        model.setPeriod(entity.getPeriod());

        return model;
    }
}
