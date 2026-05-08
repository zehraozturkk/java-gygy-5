package com.turkcell.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBookRequest(
        @NotBlank(message = "Kitap adı boş olamaz") @Size(max = 200) String name,
        @NotNull(message = "Yazar ID boş olamaz") Integer authorId
) {}
