package com.cdprojektred.javatest.productfinder;

import com.cdprojektred.javatest.lib.Query;
import com.cdprojektred.javatest.lib.QueryPolicy;
import com.cdprojektred.javatest.lib.QueryPolicyBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ReadProductPolicyBus implements QueryPolicyBus {

    @Autowired
    private List<ReadProductCommandPolicy> activePolicies;

    @Bean
    MaximumLimitPerPageCommandPolicy maximumLimitPerPageCommandPolicy() {
        return new MaximumLimitPerPageCommandPolicy();
    }

    @Override
    public void apply(Query query) {
        if (Objects.nonNull(activePolicies)) {
            activePolicies.stream()
                          .filter(policy -> policy.canBeApplied(query))
                          .forEach(policy -> policy.apply(query));
        }
    }

    abstract static class ReadProductCommandPolicy implements QueryPolicy {
    }

    @ConditionalOnProperty(name = "application.policy.maxLimit.enabled", havingValue = "true")
    @Component
    static class MaximumLimitPerPageCommandPolicy extends ReadProductCommandPolicy {

        @Value(value = "${application.policy.maxLimit.maxResultsPerPage}")
        private Integer maxResultsPerPage = 3;

        @Override
        public void apply(Query query) {
            if (query.getClass().isAssignableFrom(ProductQuery.GetPagedProductQuery.class)) {
                ((ProductQuery.GetPagedProductQuery) query).setResultsPerPage(maxResultsPerPage);
            }
        }

        @Override
        public boolean canBeApplied(Query query) {
            return query instanceof ProductQuery.GetPagedProductQuery;
        }
    }
}
