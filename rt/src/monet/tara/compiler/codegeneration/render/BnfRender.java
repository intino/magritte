package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.TreeWrapper;

import java.util.Set;

public class BnfRender extends DefaultRender {

	Set<String> identifiers;


	public BnfRender(String tplName, String projectName, Object wrapper) throws TaraException {
		super(tplName, projectName);
		this.identifiers = ((TreeWrapper) wrapper).getNodeNameLookUpTable().keySet();
	}

	@Override
	protected void init() {
		super.init();
		addMark("conceptKeys", getConceptIdentifiers());
	}

	public String getConceptIdentifiers() {
		String result = "";
		int count = 0;
		for (String identifier : identifiers) {
			count++;
			if (!identifier.isEmpty())
				result += " | " + identifier.toUpperCase() + "_KEY";
			if (count % 20 == 0) result += "\n";
		}
		return result.substring(3);
	}
}
