package com.turkcell.library_system.controller;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.service.FineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public List<FineResponse> getAll() {
        return fineService.getAll();
    }

    @GetMapping("/{id}")
    public FineResponse getById(@PathVariable Integer id) {
        return fineService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FineResponse create(@RequestBody @Valid CreateFineRequest request) {
        return fineService.create(request);
    }

    @PutMapping("/{id}")
    public FineResponse update(@PathVariable Integer id,
                               @RequestBody @Valid UpdateFineRequest request) {
        return fineService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        fineService.delete(id);
    }
}
