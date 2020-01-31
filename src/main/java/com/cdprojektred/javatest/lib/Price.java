package com.cdprojektred.javatest.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Currency;

@Embeddable
@NoArgsConstructor
@Getter
public class Price implements Serializable {
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("USD");
    private BigDecimal amount;
    private Currency currency;

    private Price(BigDecimal amount, Currency currency) {
        this.amount = amount.round(MathContext.DECIMAL32);
        this.currency = currency;
    }


    public static Price ZERO() {
        return of(BigDecimal.ZERO, DEFAULT_CURRENCY);
    }

    public static Price of(BigDecimal amount, Currency currency) {
        return new Price(amount, currency);
    }

    @Override
    public String toString() {
        return String.format("%0$.2f %s", amount, currency.toString());
    }
}
