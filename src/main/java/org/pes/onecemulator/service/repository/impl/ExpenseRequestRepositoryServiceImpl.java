package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestRepositoryServiceImpl implements ExpenseRequestRepositoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private ExpenseRequestRepository expenseRequestRepository;

    @Override
    @Transactional
    public ExpenseRequest findById(UUID id) {
        ExpenseRequest expenseRequest = expenseRequestRepository.findOne(id);
        if (expenseRequest != null && !expenseRequest.getDeleted())
            return expenseRequest;
        return null;
    }

    @Override
    @Transactional
    public ExpenseRequest findByNumber(String number) {
        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(number);
        if (expenseRequest != null && !expenseRequest.getDeleted())
            return expenseRequest;
        return null;
    }

    @Override
    @Transactional
    public List<ExpenseRequest> findAll() {
        return expenseRequestRepository.findAll()
                .stream()
                .filter(expenseRequest -> !expenseRequest.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ExpenseRequest create(ExpenseRequest expenseRequest) throws CreateEntityException {
        try {
            boolean idIsNull = expenseRequest.getId() == null;
            if (idIsNull) {
                expenseRequest.setId(UUID.randomUUID());

                return expenseRequestRepository.saveAndFlush(expenseRequest);
            } else {
                throw new CreateEntityException(500, "ExpenseRequest entity with number: " + expenseRequest.getNumber() + " exist at database");
            }
        } catch (Exception e) {
            throw new CreateEntityException(500, e.getMessage());
        }
    }

    @Override
    @Transactional
    public ExpenseRequest update(ExpenseRequest expenseRequest) throws UpdateEntityException {
        if (expenseRequestRepository.exists(expenseRequest.getId())) {
            return entityManager.merge(expenseRequest);
        } else {
            throw new UpdateEntityException(500, "Entity " + expenseRequest.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public ExpenseRequest delete(UUID id) throws DeleteEntityException {
        if (id != null) {
            ExpenseRequest expenseRequest = expenseRequestRepository.findOne(id);
            if (expenseRequest != null) {
                expenseRequest.setDeleted(true);

                return entityManager.merge(expenseRequest);
            }
            throw new DeleteEntityException(500, "Entity with id: " + id + " not exist at database");
        }
        throw new DeleteEntityException(500, "Id is null");
    }
}
