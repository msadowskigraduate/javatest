package com.cdprojektred.javatest.productfinder;

import com.cdprojektred.javatest.lib.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductQuery implements Query {

    @RequiredArgsConstructor(staticName = "ofId")
    public static class GetProductQuery extends ProductQuery {
        private final String readProductId;

        public String getId() {
            return readProductId;
        }
    }

    @RequiredArgsConstructor(staticName = "of")
    public static class GetPagedProductQuery extends ProductQuery {
        private final Integer pageNumber;

        @Setter private Integer resultsPerPage;

        public Pageable getPageQuery() {
            return PageRequest.of(pageNumber, resultsPerPage);
        }
    }
}
