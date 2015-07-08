package tafat.morphs;

import magritte.primitives.Date;
import magritte.wraps.Morph;

public class Simulation extends Morph {

    public Date from() {
        return instant(_get("into", long.class));
    }

    public Date to() {
        return instant(_get("to", long.class));
    }


}
