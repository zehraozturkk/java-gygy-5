package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.spring_cqrs.core.security.context.UserContext;
import com.turkcell.spring_cqrs.core.security.exception.AuthenticationException;
import com.turkcell.spring_cqrs.core.security.exception.AuthorizationException;

@Component
@Order(10)
public class AuthorizationBehavior implements PipelineBehavior {
    private final UserContext userContext;

    public AuthorizationBehavior(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public boolean supports(Object request) {
        return request instanceof AuthorizableRequest;
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        if (!userContext.isAuthenticated())
            throw new AuthenticationException("Bu işlem için giriş yapmalısın.");

        List<String> required = ((AuthorizableRequest) request).requiredRoles();
        if (!required.isEmpty()) {
            boolean hasRole = userContext.getRoles().stream().anyMatch(required::contains);
            if (!hasRole)
                throw new AuthorizationException("Bu işlem için yetkin bulunmuyor. Gerekli roller: " + required);
        }

        return next.invoke();
    }
}
