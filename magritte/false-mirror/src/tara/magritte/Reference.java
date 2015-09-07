package tara.magritte;


public class Reference {

	private final String qn;
	private Declaration aDeclaration;

	public Reference(String qn) {
		this.qn = qn;
	}

	public Declaration node() {
		if (aDeclaration == null) aDeclaration = PersistenceManager.loadDeclaration(qn);
		return aDeclaration;
	}
}
