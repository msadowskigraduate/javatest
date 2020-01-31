package com.cdprojektred.javatest.productfinder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReadProductRepository extends MongoRepository<ReadProduct, String> {
    ReadProduct getById(final String id);
    Page<ReadProduct> findAll(Pageable pagable);
}
