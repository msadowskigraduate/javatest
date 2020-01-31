package com.cdprojektred.javatest.lib;

public interface Factory<T, S> {
    T create(S command);
}
