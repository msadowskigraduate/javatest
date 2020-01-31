package com.cdprojektred.javatest.productfinder;

import org.springframework.data.domain.Page;

public interface ProductFinderFacade {
    //Gets a single element retrieved by ID from reactive repository
    ReadProduct handle(ProductQuery.GetProductQuery command);

    //Gets all elements from reactive repository
    Page<ReadProduct> handle(ProductQuery.GetPagedProductQuery command);
}
