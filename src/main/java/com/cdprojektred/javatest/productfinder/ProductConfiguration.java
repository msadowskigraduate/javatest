package com.cdprojektred.javatest.productfinder;

import com.cdprojektred.javatest.lib.QueryPolicy;
import com.cdprojektred.javatest.lib.QueryPolicyBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class ProductConfiguration {
    @Bean
    QueryPolicyBus policyBus() {
        return new ReadProductPolicyBus();
    }

    @Bean
    ProductFinderFacade getProductFinder(@Autowired ReadProductRepository readProductRepository,
                                         @Autowired QueryPolicyBus bus) {
        return new ProductFinderFacadeImpl(readProductRepository, bus);
    }
}
