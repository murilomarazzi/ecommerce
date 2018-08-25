package br.com.leonardoferreira.ecommerce.product.factory.base;

import br.com.leonardoferreira.jbacon.JBacon;

public abstract class NonPersistentFactory<T> extends JBacon<T> {

    @Override
    protected void persist(final T t) {
        throw new UnsupportedOperationException("Persist is doesn't a valid operation");
    }

}
