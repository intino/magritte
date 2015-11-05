package tara.lang.model.rules.custom;


import tara.lang.model.Rule;

public class Natural implements Rule<Double> {

    @Override
    public boolean accept(Double value) {
        return value > 0;
    }
}
