package io.intino.magritte.lang.model.rules.custom;


import io.intino.magritte.lang.model.rules.variable.VariableRule;

public class Natural implements VariableRule<Double> {

    @Override
    public boolean accept(Double value) {
        return value > 0;
    }
}
