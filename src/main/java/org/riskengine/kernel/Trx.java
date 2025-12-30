package org.riskengine.kernel;

import java.util.concurrent.Callable;

public abstract class Trx implements Callable<Object> {

    public Trx() {

    }

    @Override
    public abstract Object call() throws Exception;
}
