package com.cdprojektred.javatest.gateway;

import com.cdprojektred.javatest.productfinder.ProductFinderFacade;
import com.cdprojektred.javatest.productfinder.ProductQuery;
import com.cdprojektred.javatest.productfinder.ReadProduct;
import com.cdprojektred.javatest.productmutator.ProductMutatorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class ProductGateway {

    private final ProductFinderFacade productFinder;
    private final ProductMutatorFacade productMutatorFacade;

    @GetMapping("products/{id}")
    ReadProduct getProductById(@PathVariable String id) {
        return productFinder.handle(ProductQuery.GetProductQuery.ofId(id));
    }

    @PutMapping("products/{id}")
    void putProductById(@PathVariable long id, @RequestParam(required = false) String title,
                        @RequestParam(required = false) float price,
                        @RequestParam(required = false) String currencyCode) {
        productMutatorFacade.handle(ProductMutatorFacade.ProductUpdateCommand.of(id, title, BigDecimal.valueOf(price),
                currencyCode));
    }

    @DeleteMapping("products/{id}")
    void deleteProductById(@PathVariable long id) {
        productMutatorFacade.handle(ProductMutatorFacade.ProductRemoveCommand.of(id));
    }

    @GetMapping("products")
    Stream<ReadProduct> getProducts(@RequestParam Integer page) {
        return productFinder.handle(ProductQuery.GetPagedProductQuery.of(page)).get();
    }

    @PostMapping("products")
    void addProduct(@RequestBody ProductMutatorFacade.ProductInsertCommand command) {
        productMutatorFacade.handle(command);
    }
}
