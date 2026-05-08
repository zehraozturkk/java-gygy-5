package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.Officer;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.exception.UsernameAlreadyExistsException;
import com.turkcell.library_system.repository.OfficerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerService {

    private final OfficerRepository officerRepository;

    public OfficerService(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    public List<OfficerResponse> getAll() {
        return officerRepository.findAll().stream().map(this::toResponse).toList();
    }

    public OfficerResponse getById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public OfficerResponse create(CreateOfficerRequest request) {
        if (officerRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException(request.username());
        }
        Officer officer = new Officer();
        officer.setName(request.name());
        officer.setSurname(request.surname());
        officer.setUsername(request.username());
        officer.setPassword(request.password());
        return toResponse(officerRepository.save(officer));
    }

    public OfficerResponse update(Integer id, UpdateOfficerRequest request) {
        Officer officer = findOrThrow(id);
        // kullanıcı adı değiştiyse, yeni kullanıcı adı başkasına ait olmamalı
        if (!officer.getUsername().equals(request.username())
                && officerRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException(request.username());
        }
        officer.setName(request.name());
        officer.setSurname(request.surname());
        officer.setUsername(request.username());
        officer.setPassword(request.password());
        return toResponse(officerRepository.save(officer));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        officerRepository.deleteById(id);
    }

    public Officer getEntityById(Integer id) {
        return findOrThrow(id);
    }

    private Officer findOrThrow(Integer id) {
        return officerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Officer", id));
    }

    private OfficerResponse toResponse(Officer o) {
        return new OfficerResponse(o.getId(), o.getName(), o.getSurname(), o.getUsername());
    }
}
