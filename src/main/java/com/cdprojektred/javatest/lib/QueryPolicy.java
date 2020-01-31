package com.cdprojektred.javatest.lib;

public interface QueryPolicy {
    void apply(Query command);
    boolean canBeApplied(Query command);
}
