package com.firefly.domain.product.pricing.core.fees.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.product.sdk.api.FeeApplicationRuleApi;
import com.firefly.core.product.sdk.api.ProductPricingApi;
import com.firefly.domain.product.pricing.core.fees.commands.UpdateFeeApplicationRuleCommand;
import com.firefly.domain.product.pricing.core.pricing.commands.UpdateProductPricingCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateFeeApplicationRuleHandler extends CommandHandler<UpdateFeeApplicationRuleCommand, UUID> {

    private final FeeApplicationRuleApi feeApplicationRuleApi;

    public UpdateFeeApplicationRuleHandler(FeeApplicationRuleApi feeApplicationRuleApi) {
        this.feeApplicationRuleApi = feeApplicationRuleApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateFeeApplicationRuleCommand cmd) {
        return feeApplicationRuleApi.updateRule(cmd.getFeeApplicationRuleId(), cmd.getFeeComponentId(), cmd.getFeeApplicationRuleId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(feeApplicationRuleDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(feeApplicationRuleDTO).getFeeApplicationRuleId()));
    }
}