package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.AbstractObjectDto;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseRequestService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private ExpenseRequestRepositoryService expenseRequestRepositoryService;

    @Autowired
    private AccountingEntryService accountingEntryService;

    public ExpenseRequestDto getExpenseRequestById(UUID id) throws NotFoundEntityException {
        log.info("ExpenseRequest getById method start...");
        ExpenseRequest expenseRequest = expenseRequestRepositoryService.findById(id);
        if (expenseRequest != null) {
            log.info("ExpenseRequest entity with id: " + id + " found");
            return convertToDto(expenseRequest);
        }
        log.info("ExpenseRequest entity with id: " + id + " not found");
        throw new NotFoundEntityException(404, "ExpenseRequest entity with id: " + id + " not found at database");
    }

    public List<ExpenseRequestDto> listExpenseRequest() throws NotFoundEntityException {
        log.info("ExpenseRequest list method start...");
        List<ExpenseRequest> expenseRequests = expenseRequestRepositoryService.findAll();
        if (expenseRequests.size() > 0) {
            log.info("ExpenseRequest entity list count: " + expenseRequests.size());
            return convertToDto(expenseRequests);
        }
        log.warn("ExpenseRequest entity list count = 0");
        throw new NotFoundEntityException(404, "ExpenseRequest entity list count = 0");
    }

    public ExpenseRequestDto createExpenseRequest(ExpenseRequestDto expenseRequestDto) throws CreateEntityException {
        log.info("ExpenseRequest create method start...");
        try {
            if (expenseRequestDto != null && expenseRequestDto.getNumber() != null) {
                log.info("ExpenseRequest: " + expenseRequestDto.toString());
                ExpenseRequestDto result = convertToDto(expenseRequestRepositoryService.create(convertToEntity(expenseRequestDto)));
                log.info("ExpenseRequest created: " + result.toString());

                return result;
            }
        } catch (Exception e) {
            log.error("ExpenseRequest create error: " + e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
            throw new CreateEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        log.error("ExpenseRequest entity is null or has not expenseNumber value");
        throw new CreateEntityException(500, "ExpenseRequest entity is null or has not expenseNumber value");
    }

    public ExpenseRequestDto updateExpenseRequest(ExpenseRequestDto expenseRequestDto) throws UpdateEntityException {
        log.info("ExpenseRequest update method start...");
        try {
            if (expenseRequestDto != null && expenseRequestDto.getId() != null && expenseRequestDto.getNumber() != null) {
                ExpenseRequestDto tmp = convertToDto(expenseRequestRepositoryService.findById(expenseRequestDto.getId()));
                if(tmp != null) {
                    log.info("ExpenseRequest: " + expenseRequestDto.toString());
                    tmp.setConfirm(expenseRequestDto.getConfirm());
                    tmp.setCurrency(expenseRequestDto.getCurrency());
                    tmp.setPaid(expenseRequestDto.getPaid());
                    tmp.setSum(expenseRequestDto.getSum());
                    ExpenseRequestDto result = convertToDto(expenseRequestRepositoryService.update(convertToEntity(tmp)));
                    log.info("ExpenseRequest updated: " + result.toString());

                    return result;
                }
            }
        } catch (Exception e) {
            throw new UpdateEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        throw new UpdateEntityException(500, "ExpenseRequest entity is null");
    }

    public ExpenseRequestDto deleteExpenseRequest(ExpenseRequestDto expenseRequestDto) throws DeleteEntityException {
        log.info("ExpenseRequest delete method start...");
        try {
            if (expenseRequestDto != null) {
                ExpenseRequestDto expenseRequestDtoTmp = convertToDto(expenseRequestRepositoryService.findById(expenseRequestDto.getId()));
                expenseRequestDtoTmp.setDeleted(true);
                log.info("ExpenseRequest " + expenseRequestDto.toString() + " set deleted");
                Set<AccountingEntryDto> accountingEntryDtos = expenseRequestDtoTmp.getAccountingEntries();
                log.info("Size of AccountingEntries: "+ accountingEntryDtos.size());
                if (accountingEntryDtos.size() > 0) {
                    for (AccountingEntryDto a : accountingEntryDtos) {
                        a.setDeleted(true);
                    }
                    expenseRequestDtoTmp.setAccountingEntries(accountingEntryDtos);
                }
                ExpenseRequestDto expenseRequestDtoResult = convertToDto(expenseRequestRepositoryService.update(convertToEntity(expenseRequestDtoTmp)));
                log.info("ExpenseRequest deleted result: "
                        + "id = " + expenseRequestDtoResult.getId()
                        + "\n" + "AccountingEntries list id = " + expenseRequestDtoResult.getAccountingEntries()
                        .stream()
                        .map(AbstractObjectDto::getId)
                        .collect(Collectors.toList())
                );

                return expenseRequestDtoResult;
            }
        } catch (Exception e) {
            throw new DeleteEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        throw new DeleteEntityException(500, "Delete method argument is null");
    }

    public ExpenseRequestDto getExpenseRequestByNumber(String number) throws NotFoundEntityException {
        log.info("ExpenseRequest getByNumber method start...");
        ExpenseRequest expenseRequest = expenseRequestRepositoryService.findByNumber(number);
        if (expenseRequest != null) {
            log.info("ExpenseRequest entity with number: " + number + " found");
            return convertToDto(expenseRequest);
        }
        log.info("ExpenseRequest entity with number: " + number + " not found");
        throw new NotFoundEntityException(404, "Entity with number: " + number + " not found at database");
    }

    private ExpenseRequestDto convertToDto(ExpenseRequest expenseRequest) {
        return mapperFactoryService.getMapper().map(expenseRequest, ExpenseRequestDto.class);
    }

    private List<ExpenseRequestDto> convertToDto(List<ExpenseRequest> accountingEntries) {
        return accountingEntries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ExpenseRequest convertToEntity(ExpenseRequestDto accountingEntryDto) {
        return mapperFactoryService.getMapper().map(accountingEntryDto, ExpenseRequest.class);
    }
}
