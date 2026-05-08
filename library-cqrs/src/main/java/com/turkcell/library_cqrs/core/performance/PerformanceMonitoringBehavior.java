package com.turkcell.library_cqrs.core.performance;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class PerformanceMonitoringBehavior implements PipelineBehavior {

    private static final long WARNING_THRESHOLD_MS = 3000;

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        long start = System.currentTimeMillis();
        R result = next.invoke();
        long elapsed = System.currentTimeMillis() - start;

        if (elapsed > WARNING_THRESHOLD_MS) {
            System.out.printf("[PERFORMANCE WARNING] %s took %d ms — threshold: %d ms%n",
                    request.getClass().getSimpleName(), elapsed, WARNING_THRESHOLD_MS);
        }

        return result;
    }
}
