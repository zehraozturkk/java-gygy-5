package com.turkcell.library_system.dto;

public record BookResponse(
        Integer id,
        String name,
        Integer authorId,
        String authorFullName
) {}
