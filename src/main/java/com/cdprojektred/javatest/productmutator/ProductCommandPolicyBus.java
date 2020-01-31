package com.cdprojektred.javatest.productmutator;

import com.cdprojektred.javatest.lib.Command;
import com.cdprojektred.javatest.lib.CommandPolicy;
import com.cdprojektred.javatest.lib.CommandPolicyBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.cdprojektred.javatest.productmutator.ProductMutatorFacade.ProductInsertCommand;

@Service
@RequiredArgsConstructor
class ProductCommandPolicyBus implements CommandPolicyBus {

    private final ProductRepository repository;

    @Autowired
    private List<MutateProductCommandPolicy> activePolicies;

    @Override
    public void apply(Command command) {
        if(Objects.nonNull(activePolicies)) {
            activePolicies.stream()
                          .filter(policy -> policy.canBeApplied(command))
                          .forEach(policy -> policy.apply(command));
        }
    }

    @Bean
    MutateProductCommandPolicy uniqueTitlePolicy() {
        return new UniqueTitleProductCommandPolicy(repository);
    }

    abstract static class MutateProductCommandPolicy implements CommandPolicy {
        protected final ProductRepository repository;

        protected MutateProductCommandPolicy(ProductRepository repository) {
            this.repository = repository;
        }
    }

    static class UniqueTitleProductCommandPolicy extends MutateProductCommandPolicy {

        UniqueTitleProductCommandPolicy(ProductRepository repository) {
            super(repository);
        }

        @Override
        public void apply(Command command) {
            if (command instanceof ProductInsertCommand) {
                String commandTitle = ((ProductInsertCommand) command).getTitle();
                if(repository.findByTitle(commandTitle).isPresent()) {
                    throw new NonUniqueTitleException();
                }
            }
        }

        @Override
        public boolean canBeApplied(Command command) {
            return command instanceof ProductInsertCommand;
        }
    }
}
