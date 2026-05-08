package com.turkcell.library_cqrs.core.logging;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(20)
public class LoggingBehavior implements PipelineBehavior {

    @Override
    public boolean supports(Object request) {
        return !(request instanceof NotLoggableRequest);
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.printf("[REQUEST ] %s -> %s%n",
                request.getClass().getSimpleName(), request);

        R response = next.invoke();

        System.out.printf("[RESPONSE] %s -> %s%n",
                request.getClass().getSimpleName(), response);

        return response;
    }
}
