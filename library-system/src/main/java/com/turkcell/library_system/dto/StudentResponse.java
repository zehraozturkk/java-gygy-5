package com.turkcell.library_system.dto;

public record StudentResponse(
        Integer id,
        String name,
        String surname,
        String phone
) {}
