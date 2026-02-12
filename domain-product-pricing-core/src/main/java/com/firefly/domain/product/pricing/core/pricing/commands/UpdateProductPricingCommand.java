package com.firefly.domain.product.pricing.core.pricing.commands;

import com.firefly.common.cqrs.command.Command;
import com.firefly.core.product.sdk.model.ProductDTO;
import com.firefly.core.product.sdk.model.ProductPricingDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductPricingCommand extends ProductPricingDTO implements Command<UUID> {
    private UUID productPricingId;

    public UpdateProductPricingCommand withProductPricingId(UUID productPricingId){
        this.setProductPricingId(productPricingId);
        return this;
    }
}
