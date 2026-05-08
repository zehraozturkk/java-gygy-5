package com.turkcell.library_system.controller;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.service.OfficerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officers")
public class OfficerController {

    private final OfficerService officerService;

    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @GetMapping
    public List<OfficerResponse> getAll() {
        return officerService.getAll();
    }

    @GetMapping("/{id}")
    public OfficerResponse getById(@PathVariable Integer id) {
        return officerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfficerResponse create(@RequestBody @Valid CreateOfficerRequest request) {
        return officerService.create(request);
    }

    @PutMapping("/{id}")
    public OfficerResponse update(@PathVariable Integer id,
                                  @RequestBody @Valid UpdateOfficerRequest request) {
        return officerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        officerService.delete(id);
    }
}
