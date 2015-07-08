package magritte.dsl;

import magritte.schema.Box;
import magritte.ontology.MonetMain;

public class MonetDsl extends Box.Dsl {
    public static final Box box = new MonetDsl();

    @Override
    protected Box[] includes() {
        return $(MonetMain.box);
    }

}
