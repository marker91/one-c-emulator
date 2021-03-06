package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    @Autowired
    public SourceServiceImpl(final SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public Source getById(final UUID id) throws NotFoundException {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Source.class, id));
    }

    @Override
    public Source getByName(final String name) throws NotFoundException {
        return sourceRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(Source.class, "name:" + name));
    }

    @Override
    public List<Source> list() {
        return sourceRepository.findAll();
    }

    @Transactional
    @Override
    public Source create(final SourceModel model) throws ValidationException {
        validateModel(model);

        return updateOrCreate(model, new Source());
    }

    @Transactional
    @Override
    public Source update(final SourceModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final Source source = sourceRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Source.class, model.getId()));

        return updateOrCreate(model, source);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        sourceRepository.deleteById(id);
    }

    private Source updateOrCreate(final SourceModel model, final Source source) {
        source.setName(model.getName());
        return sourceRepository.save(source);
    }

    private void validateModel(final SourceModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getName() == null) {
            throw new ValidationException("Name is null.");
        }

        if (model.getName().isEmpty()) {
            throw new ValidationException("Name is empty.");
        }
    }
}
