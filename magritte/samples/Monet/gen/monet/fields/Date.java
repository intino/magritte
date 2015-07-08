package monet.fields;

import magritte.Expression;
import monet.Field;

public class Date extends Field {

    public magritte.primitives.Date value() {
        return _get("value").asDate();
    }

    public void value(magritte.primitives.Date value) {
        _edit().set("value", value);
    }

    public void value(Expression<magritte.primitives.Date> value) {
        _edit().let("value", value);
    }


}
