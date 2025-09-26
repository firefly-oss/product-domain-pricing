package com.firefly.domain.product.pricing.core.pricing.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.common.product.sdk.api.ProductPricingApi;
import com.firefly.domain.product.pricing.core.pricing.commands.UpdateProductPricingCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdatePricingHandler extends CommandHandler<UpdateProductPricingCommand, UUID> {

    private final ProductPricingApi productPricingApi;

    public UpdatePricingHandler(ProductPricingApi productPricingApi) {
        this.productPricingApi = productPricingApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateProductPricingCommand cmd) {
        return productPricingApi.updatePricing(cmd.getProductId(), cmd.getProductPricingId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(productPricingDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(productPricingDTO).getProductId()));
    }
}