package org.pes.onecemulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "accounting_entry",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "code",
                name = "uk_accounting_entry_code"
        )
)
public class AccountingEntry extends AbstractEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "expense_request_id",
            foreignKey = @ForeignKey(name = "fk_accounting_entry_expense_request_id"))
    private ExpenseRequest expenseRequest;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public ExpenseRequest getExpenseRequest() {
        return expenseRequest;
    }

    public void setExpenseRequest(ExpenseRequest expenseRequest) {
        this.expenseRequest = expenseRequest;
    }
}
