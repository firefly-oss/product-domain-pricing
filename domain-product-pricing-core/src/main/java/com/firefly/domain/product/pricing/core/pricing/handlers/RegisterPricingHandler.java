package com.firefly.domain.product.pricing.core.pricing.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.product.sdk.api.ProductPricingApi;
import com.firefly.domain.product.pricing.core.pricing.commands.RegisterProductPricingCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPricingHandler extends CommandHandler<RegisterProductPricingCommand, UUID> {

    private final ProductPricingApi productPricingApi;

    public RegisterPricingHandler(ProductPricingApi productPricingApi) {
        this.productPricingApi = productPricingApi;
    }

    @Override
    protected Mono<UUID> doHandle(com.firefly.domain.product.pricing.core.pricing.commands.RegisterProductPricingCommand cmd) {
        return productPricingApi.createPricing(cmd.getProductId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(productPricingDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(productPricingDTO)).getProductPricingId());
    }
}