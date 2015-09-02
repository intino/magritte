package tara.magritte;


public class Reference {

	private final String qn;
	private Case aCase;

	public Reference(String qn) {
		this.qn = qn;
	}

	public Case node() {
		if (aCase == null) aCase = PersistenceManager.loadNode(qn);
		return aCase;
	}
}
