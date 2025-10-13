package com.firefly.domain.product.pricing.core.fees.commands;

import com.firefly.common.cqrs.command.Command;

import java.util.UUID;

public record RemoveFeeApplicationRuleCommand(
        UUID feeApplicationRuleId,
        UUID feeStructureId,
        UUID componentId
) implements Command<Void>{}