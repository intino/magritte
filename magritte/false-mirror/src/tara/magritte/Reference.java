package tara.magritte;


import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Reference {

	String name;
    DynamicModel model;
    Instance instance;
	LocalDateTime time = now();

	Reference() {
	}

	public Reference(String name, DynamicModel model) {
		this.name = name;
        this.model = model;
		model.register(this);
    }

	public Reference(Instance instance) {
		this(instance.name, (DynamicModel) instance.model());
		this.instance = instance;
    }

	public String name() {
		return name;
	}

	public Instance instance() {
		time = now();
		if (instance == null) instance = model.loadInstance(this);
		return instance;
	}

	Instance free() {
		Instance result = instance;
		instance = null;
		time = now();
		return result;
	}
}
