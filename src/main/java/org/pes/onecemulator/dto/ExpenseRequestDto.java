package org.pes.onecemulator.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ExpenseRequestDto extends AbstractObjectDto {

    private String source;

    private String currency;

    private Boolean isConfirm;

    private Boolean isPaid;

    private String number;

    private BigDecimal sum;

    private Set<AccountingEntryDto> accountingEntries = new HashSet<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getConfirm() {
        return isConfirm;
    }

    public void setConfirm(Boolean confirm) {
        isConfirm = confirm;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Set<AccountingEntryDto> getAccountingEntries() {
        return accountingEntries;
    }

    public void setAccountingEntries(Set<AccountingEntryDto> accountingEntries) {
        this.accountingEntries = accountingEntries;
    }
}
