package tara.lang.model.rules.custom;


import tara.lang.model.rules.variable.VariableRule;

public class Natural implements VariableRule<Double> {

    @Override
    public boolean accept(Double value) {
        return value > 0;
    }
}
