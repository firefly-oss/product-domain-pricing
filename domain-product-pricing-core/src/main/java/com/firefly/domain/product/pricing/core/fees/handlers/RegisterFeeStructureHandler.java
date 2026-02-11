package com.firefly.domain.product.pricing.core.fees.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.common.product.sdk.api.FeeStructureApi;
import com.firefly.domain.product.pricing.core.fees.commands.RegisterFeeStructureCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterFeeStructureHandler extends CommandHandler<RegisterFeeStructureCommand, UUID> {

    private final FeeStructureApi feeStructureApi;

    public RegisterFeeStructureHandler(FeeStructureApi feeStructureApi) {
        this.feeStructureApi = feeStructureApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterFeeStructureCommand cmd) {
        return feeStructureApi.createFeeStructure1(cmd, UUID.randomUUID().toString())
                .mapNotNull(feeStructureDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(feeStructureDTO)).getFeeStructureId());
    }
}