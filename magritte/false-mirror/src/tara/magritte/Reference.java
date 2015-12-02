package tara.magritte;


public class Reference {

	private final String qn;
    private Model model;
    private Instance instance;

	public Reference(String qn, Model model) {
		this.qn = qn;
        this.model = model;
    }

	public Instance declaration() {
		if (instance == null) instance = model.loadDeclaration(qn);
		return instance;
	}
}
