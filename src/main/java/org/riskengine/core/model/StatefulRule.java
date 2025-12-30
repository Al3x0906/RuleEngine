package org.riskengine.core.model;

import org.riskengine.core.kernel.ValidationContext;

public interface StatefulRule extends Rule{


    default  void commit(ValidationContext context){

    }
    default void rollback(ValidationContext context){

    }
}
