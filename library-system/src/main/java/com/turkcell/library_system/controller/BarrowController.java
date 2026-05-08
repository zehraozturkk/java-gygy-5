package com.turkcell.library_system.controller;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.service.BarrowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BarrowController {

    private final BarrowService barrowService;

    public BarrowController(BarrowService barrowService) {
        this.barrowService = barrowService;
    }

    @GetMapping
    public List<BarrowResponse> getAll() {
        return barrowService.getAll();
    }

    @GetMapping("/{id}")
    public BarrowResponse getById(@PathVariable Integer id) {
        return barrowService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarrowResponse create(@RequestBody @Valid CreateBarrowRequest request) {
        return barrowService.create(request);
    }

    // Güncelleme: yalnızca iade tarihi ve durumu değiştirilir
    @PutMapping("/{id}")
    public BarrowResponse update(@PathVariable Integer id,
                                 @RequestBody @Valid UpdateBarrowRequest request) {
        return barrowService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        barrowService.delete(id);
    }
}
