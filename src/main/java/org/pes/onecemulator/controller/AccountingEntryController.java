package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.service.api.AccountingEntryService;
import org.pes.onecemulator.service.api.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/entry")
public class AccountingEntryController {

    @Autowired
    private AccountingEntryService accountingEntryService;

    @Autowired
    private CrmInteractionService crmInteractionService;

    @RequestMapping(method = RequestMethod.GET, path = "/getbyid/{id}")
    public @ResponseBody ResponseEntity<AccountingEntryDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.getAccountingEntryById(UUID.fromString(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public @ResponseBody ResponseEntity<List<AccountingEntryDto>> list() {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.listAccountingEntry(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public @ResponseBody ResponseEntity<AccountingEntryDto> create(@RequestBody AccountingEntryDto accountingEntryDto) {
        try {
            AccountingEntryDto accountingEntryDtoResult = accountingEntryService.createAccountingEntry(accountingEntryDto);
            crmInteractionService.sendAccountingEntryToCrm(accountingEntryDtoResult);
            return new ResponseEntity<>(
                    accountingEntryDtoResult,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public @ResponseBody ResponseEntity<AccountingEntryDto> update(@RequestBody AccountingEntryDto accountingEntryDto) throws Exception {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.updateAccountingEntry(accountingEntryDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public ResponseEntity<AccountingEntryDto> delete(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.deleteAccountingEntry(UUID.fromString(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
