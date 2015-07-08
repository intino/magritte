package magritte.dsl;

import magritte.schema.Box;
import magritte.ontology.CensusMain;

public class CensusDsl extends Box.Dsl {
    public static final Box box = new CensusDsl();

    @Override
    protected Box[] includes() {
        return $(CensusMain.box);
    }
}
