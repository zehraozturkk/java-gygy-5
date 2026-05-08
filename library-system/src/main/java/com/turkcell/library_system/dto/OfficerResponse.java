package com.turkcell.library_system.dto;

public record OfficerResponse(
        Integer id,
        String name,
        String surname,
        String username
        // password döndürülmüyor
) {}
