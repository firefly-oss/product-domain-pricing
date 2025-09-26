package com.firefly.domain.product.pricing.core.fees.services.impl;

import com.firefly.domain.product.pricing.core.fees.commands.RegisterFeeSchemaCommand;
import com.firefly.domain.product.pricing.core.fees.commands.UpdateFeeApplicationRuleCommand;
import com.firefly.domain.product.pricing.core.fees.services.FeesService;
import com.firefly.domain.product.pricing.core.fees.workflows.RegisterFeeSchemaSaga;
import com.firefly.domain.product.pricing.core.fees.workflows.UpdateFeeRuleSaga;
import com.firefly.domain.product.pricing.core.pricing.workflows.UpdatePricingSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FeeServiceImpl implements FeesService {

    private final SagaEngine engine;

    @Autowired
    public FeeServiceImpl(SagaEngine engine){
        this.engine=engine;
    }


    @Override
    public Mono<SagaResult> defineFeeScheme(RegisterFeeSchemaCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RegisterFeeSchemaSaga::registerFeeStructure, command.getFeeStructure())
                .forStep(RegisterFeeSchemaSaga::registerFeeComponent, command.getFeeComponent())
                .forStep(RegisterFeeSchemaSaga::registerFeeApplicationRule, command.getFeeApplicationRule())
                .forStep(RegisterFeeSchemaSaga::registerProductFeeStructure, command.getProductFeeStructure())
                .build();
        return engine.execute(RegisterFeeSchemaSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> updateFeeRule(UpdateFeeApplicationRuleCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateFeeRuleSaga::updateFeeApplicationRule, command)
                .build();
        return engine.execute(UpdateFeeRuleSaga.class, inputs);
    }
}
