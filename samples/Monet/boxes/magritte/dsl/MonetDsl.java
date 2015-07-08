package magritte.dsl;

import magritte.ontology.MonetMain;
import magritte.schema.Box;

public class MonetDsl extends Box.Dsl {
	public static final Box box = new MonetDsl();

	@Override
	protected Box[] includes() {
		return $(MonetMain.box);
	}

}
