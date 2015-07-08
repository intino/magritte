package monet.fields;

import magritte.Expression;
import monet.Field;

public class Number extends Field {

    public double value() {
        return _get("value").asDouble();
    }

    public void value(double value) {
        _edit().set("value", value);
    }

    public void value(Expression<Double> value) {
        _edit().let("value", value);
    }


}
