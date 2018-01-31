package org.pes.onecemulator.dto;

import java.util.HashSet;
import java.util.Set;

public class PayerDto extends AbstractObjectDto {

    private String address;

    private String code;

    private String fullName;

    private String inn;

    private String kpp;

    private String name;

    private Set<InvoiceDto> invoices = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<InvoiceDto> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceDto> invoices) {
        this.invoices = invoices;
    }
}
