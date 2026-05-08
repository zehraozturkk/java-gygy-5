package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.*;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FineService {

    private final FineRepository fineRepository;
    private final BarrowService barrowService;
    private final OfficerService officerService;

    public FineService(FineRepository fineRepository,
                       BarrowService barrowService,
                       OfficerService officerService) {
        this.fineRepository = fineRepository;
        this.barrowService = barrowService;
        this.officerService = officerService;
    }

    public List<FineResponse> getAll() {
        return fineRepository.findAll().stream().map(this::toResponse).toList();
    }

    public FineResponse getById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public FineResponse create(CreateFineRequest request) {
        Barrow barrow = barrowService.getEntityById(request.barrowId());
        Officer officer = officerService.getEntityById(request.officerId());

        Fine fine = new Fine();
        fine.setBarrow(barrow);
        fine.setOfficer(officer);
        fine.setAmount(request.amount());
        fine.setIssuedDate(LocalDate.now());
        fine.setPaid(false);

        return toResponse(fineRepository.save(fine));
    }

    public FineResponse update(Integer id, UpdateFineRequest request) {
        Fine fine = findOrThrow(id);
        fine.setAmount(request.amount());
        fine.setPaid(request.isPaid());
        return toResponse(fineRepository.save(fine));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        fineRepository.deleteById(id);
    }

    private Fine findOrThrow(Integer id) {
        return fineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fine", id));
    }

    private FineResponse toResponse(Fine f) {
        Student student = f.getBarrow().getStudent();
        String studentFull = student.getName() + " " + student.getSurname();
        String officerFull = f.getOfficer().getName() + " " + f.getOfficer().getSurname();
        return new FineResponse(
                f.getId(),
                f.getBarrow().getId(), studentFull,
                f.getOfficer().getId(), officerFull,
                f.getAmount(), f.getIssuedDate(), f.isPaid()
        );
    }
}
