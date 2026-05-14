package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.List;

public interface AuthorizableRequest {
    // Boş liste -> sadece login kontrolü. Dolu liste -> rol de kontrol edilir.
    default List<String> requiredRoles() {
        return List.of();
    }
}
