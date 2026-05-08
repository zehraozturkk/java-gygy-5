package com.turkcell.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorRequest(
        @NotBlank(message = "İsim boş olamaz") @Size(max = 100) String name,
        @NotBlank(message = "Soyisim boş olamaz") @Size(max = 100) String surname
) {}
