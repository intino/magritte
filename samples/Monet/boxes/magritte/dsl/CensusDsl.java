package magritte.dsl;

import magritte.ontology.CensusMain;
import magritte.schema.Box;

public class CensusDsl extends Box.Dsl {
	public static final Box box = new CensusDsl();

	@Override
	protected Box[] includes() {
		return $(CensusMain.box);
	}
}
