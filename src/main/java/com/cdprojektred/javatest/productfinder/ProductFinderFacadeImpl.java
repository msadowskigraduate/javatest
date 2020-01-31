package com.cdprojektred.javatest.productfinder;

import com.cdprojektred.javatest.lib.QueryPolicyBus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class ProductFinderFacadeImpl implements ProductFinderFacade {
    private final ReadProductRepository readProductRepository;
    private final QueryPolicyBus policyBus;

    public ReadProduct handle(ProductQuery.GetProductQuery query) {
        policyBus.apply(query);
        //Get product from repo
        return readProductRepository.getById(query.getId());
    }

    public Page<ReadProduct> handle(ProductQuery.GetPagedProductQuery query) {
        policyBus.apply(query);
        return readProductRepository.findAll(query.getPageQuery());
    }
}
