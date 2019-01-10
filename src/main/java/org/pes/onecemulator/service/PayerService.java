package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.List;
import java.util.UUID;

public interface PayerService {

    PayerModel getById(UUID id) throws NotFoundException;

    List<PayerModel> list();

    List<PayerModel> listBySource(String source);

    PayerModel create(PayerModel model) throws Exception;

    List<PayerModel> create(List<PayerModel> models) throws Exception;

    PayerModel update(PayerModel model) throws Exception;

    void delete(UUID id);
}
