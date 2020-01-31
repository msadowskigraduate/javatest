package com.cdprojektred.javatest.lib;

public interface CommandPolicyBus {
    void apply(Command command);
}
