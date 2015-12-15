package tara.magritte;


import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Reference {

	final String qn;
    final DynamicModel model;
    Instance instance;
	LocalDateTime time = now();

	public Reference(String qn, DynamicModel model) {
		this.qn = qn;
        this.model = model;
		model.register(this);
    }

	public Reference(Instance instance) {
		this(instance.name, (DynamicModel) instance.model());
		this.instance = instance;
    }

	public Instance instance() {
		time = now();
		if (instance == null) instance = model.loadInstance(qn);
		return instance;
	}

	void free() {
		instance = null;
		time = now();
	}
}
