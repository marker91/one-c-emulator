package org.pes.onecemulator.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class AccountingEntryDto extends AbstractObjectDto {

    private String code;

    private Calendar date;

    private String documentName;

    private String expenseNumber;

    private ExpenseRequestDto expenseRequest;

    private BigDecimal sum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getExpenseNumber() {
        return expenseNumber;
    }

    public void setExpenseNumber(String expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public ExpenseRequestDto getExpenseRequest() {
        return expenseRequest;
    }

    public void setExpenseRequest(ExpenseRequestDto expenseRequest) {
        this.expenseRequest = expenseRequest;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
