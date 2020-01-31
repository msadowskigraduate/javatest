package com.cdprojektred.javatest.lib;

public interface CommandPolicy {
    void apply(Command command);
    boolean canBeApplied(Command command);
}
