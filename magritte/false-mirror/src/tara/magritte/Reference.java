package tara.magritte;


public class Reference {

	private final String qn;
    private Model model;
    private Declaration declaration;

	public Reference(String qn, Model model) {
		this.qn = qn;
        this.model = model;
    }

	public Declaration declaration() {
		if (declaration == null) declaration = model.loadDeclaration(qn);
		return declaration;
	}
}
