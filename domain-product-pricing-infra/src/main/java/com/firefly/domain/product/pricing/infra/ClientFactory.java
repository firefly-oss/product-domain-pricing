package com.firefly.domain.product.pricing.infra;

import com.firefly.common.product.sdk.api.*;
import com.firefly.common.product.sdk.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the ClientFactory interface.
 * Creates client service instances using the appropriate API clients and dependencies.
 */
@Component
public class ClientFactory {

    private final ApiClient apiClient;

    @Autowired
    public ClientFactory(
            ProductMgmtProperties productMgmtProperties) {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath(productMgmtProperties.getBasePath());
    }

    @Bean
    public ProductPricingApi productPricingApi() {
        return new ProductPricingApi(apiClient);
    }

    @Bean
    public ProductFeeStructureApi productFeeStructureApi() {
        return new ProductFeeStructureApi(apiClient);
    }

    @Bean
    public FeeStructureApi feeStructureApi() {
        return new FeeStructureApi(apiClient);
    }

    @Bean
    public ProductFeeComponentApi productFeeComponentApi() {
        return new ProductFeeComponentApi(apiClient);
    }

    @Bean
    public FeeApplicationRuleApi feeApplicationRuleApi() {
        return new FeeApplicationRuleApi(apiClient);
    }

}
