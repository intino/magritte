package tafat.morphs;

import tara.magritte.primitives.Date;
import tara.magritte.wraps.Morph;

public class Simulation extends Morph {

	public Date from() {
		return instant(_get("into", long.class));
	}

	public Date to() {
		return instant(_get("to", long.class));
	}


}
