package com.cdprojektred.javatest.productfinder;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Document
@Value
public class ReadProduct {
    @Id
    private final String id;
    private final String title;
    private final BigDecimal price;
    private final String currency;
}