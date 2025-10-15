package com.firefly.domain.product.pricing.core.pricing.workflows;

import com.firefly.common.cqrs.command.CommandBus;
import com.firefly.domain.product.pricing.core.pricing.commands.RegisterProductPricingCommand;
import com.firefly.transactional.saga.annotations.Saga;
import com.firefly.transactional.saga.annotations.SagaStep;
import com.firefly.transactional.saga.annotations.StepEvent;
import com.firefly.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.product.pricing.core.utils.constants.ProductPricingConstants.*;


@Saga(name = SAGA_REGISTER_PRICING)
@Service
public class RegisterPricingSaga {

    private final CommandBus commandBus;

    @Autowired
    public RegisterPricingSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REGISTER_PRODUCT_PRICING)
    @StepEvent(type = EVENT_PRODUCT_PRICING_REGISTERED)
    public Mono<UUID> registerPricing(RegisterProductPricingCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }


}
