package com.firefly.domain.product.pricing.core.fees.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.common.product.sdk.api.FeeApplicationRuleApi;
import com.firefly.domain.product.pricing.core.fees.commands.RegisterFeeApplicationRuleCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterFeeApplicationRuleHandler extends CommandHandler<RegisterFeeApplicationRuleCommand, UUID> {

    private final FeeApplicationRuleApi feeApplicationRuleApi;

    public RegisterFeeApplicationRuleHandler(FeeApplicationRuleApi feeApplicationRuleApi) {
        this.feeApplicationRuleApi = feeApplicationRuleApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterFeeApplicationRuleCommand cmd) {
        return feeApplicationRuleApi.createRule(UUID.randomUUID(), cmd.getFeeComponentId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(feeApplicationRuleDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(feeApplicationRuleDTO)).getFeeApplicationRuleId());
    }
}