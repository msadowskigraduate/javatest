package com.cdprojektred.javatest.productmutator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductMutatorFacadeImpl.Product, Long> {

    @NonNull
    ProductMutatorFacadeImpl.Product saveAndFlush(@NonNull ProductMutatorFacadeImpl.Product product);

    Optional<ProductMutatorFacadeImpl.Product> findByTitle(String title);
}
