package tara.magritte;


public class Reference {

	private final String qn;
    private final Model model;
    private Instance instance;

	public Reference(String qn, Model model) {
		this.qn = qn;
        this.model = model;
    }

	public Instance instance() {
		if (instance == null) instance = model.loadInstance(qn);
		return instance;
	}
}
