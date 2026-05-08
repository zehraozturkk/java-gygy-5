package com.turkcell.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOfficerRequest(
        @NotBlank(message = "İsim boş olamaz") @Size(max = 100) String name,
        @NotBlank(message = "Soyisim boş olamaz") @Size(max = 100) String surname,
        @NotBlank(message = "Kullanıcı adı boş olamaz") @Size(max = 50) String username,
        @NotBlank(message = "Şifre boş olamaz") @Size(min = 6, max = 255) String password
) {}
