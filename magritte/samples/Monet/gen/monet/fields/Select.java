package monet.fields;

import monet.Field;
import monet.Thesaurus;

public class Select extends Field {

    public Thesaurus.Term value() {
        return _get("value").as(Thesaurus.Term.class);
    }

    public void value(Thesaurus.Term value) {
        _edit().set("value", value);
    }


}
