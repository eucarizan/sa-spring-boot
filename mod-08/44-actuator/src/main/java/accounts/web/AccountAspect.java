package accounts.web;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountAspect {
    private Counter counter;

    public AccountAspect(MeterRegistry registry) {
        this.counter = registry.counter("account.fetch", "type", "fromAspect");
    }

    @Before("execution(* accounts.web.AccountController.accountSummary(..))")
    public void increment() {
        counter.increment();
    }
}
