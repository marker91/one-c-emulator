package org.pes.onecemulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "invoice",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "number",
                        name = "uk_invoice_number"),
                @UniqueConstraint(
                        columnNames = "external_id",
                        name = "uk_invoice_external_id"
                )
        }
)
public class Invoice extends AbstractEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "number_oq")
    private String numberOq;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    @Column(name = "status")
    private String status;

    @Column(name = "sum")
    private BigDecimal invoiceSum;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    @Column(name = "payment_sum_currency", nullable = true)
    private String paymentSumWithCurrencyPayment;

    @Column(name = "sum_rub", nullable = true)
    private String invoiceSumRUB;

    @Column(name = "payment_currency", nullable = true)
    private String paymentCurrency;

    @Column(name = "invoice_currency", nullable = true)
    private String invoiceCurrency;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id", foreignKey = @ForeignKey(name = "fk_invoice_payer_id"))
    private Payer payer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_id", foreignKey = @ForeignKey(name = "fk_invoice_source_id"))
    private Source source;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberOq() {
        return numberOq;
    }

    public void setNumberOq(String numberOq) {
        this.numberOq = numberOq;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getInvoiceSum() {
        return invoiceSum;
    }

    public void setInvoiceSum(BigDecimal invoiceSum) {
        this.invoiceSum = invoiceSum;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getPaymentSumWithCurrencyPayment() { return paymentSumWithCurrencyPayment; }

    public void setPaymentSumWithCurrencyPayment(String paymentSumWithCurrencyPayment) { this.paymentSumWithCurrencyPayment = paymentSumWithCurrencyPayment; }

    public String getInvoiceSumRUB() { return invoiceSumRUB; }

    public void setInvoiceSumRUB(String invoiceSumRUB) { this.invoiceSumRUB = invoiceSumRUB; }

    public String getPaymentCurrency() { return paymentCurrency; }

    public void setPaymentCurrency(String paymentCurrency) { this.paymentCurrency = paymentCurrency; }

    public String getInvoiceCurrency() { return invoiceCurrency; }

    public void setInvoiceCurrency(String invoiceCurrency) { this.invoiceCurrency = invoiceCurrency; }
}
