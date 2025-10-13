package com.firefly.domain.product.pricing.core.fees.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.common.product.sdk.api.ProductFeeComponentApi;
import com.firefly.domain.product.pricing.core.fees.commands.RegisterFeeComponentCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterFeeComponentHandler extends CommandHandler<RegisterFeeComponentCommand, UUID> {

    private final ProductFeeComponentApi productFeeComponentApi;

    public RegisterFeeComponentHandler(ProductFeeComponentApi productFeeComponentApi) {
        this.productFeeComponentApi = productFeeComponentApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterFeeComponentCommand cmd) {
        return productFeeComponentApi.createFeeComponent(cmd.getFeeStructureId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(feeComponentDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(feeComponentDTO)).getFeeComponentId());
    }
}