package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class Batch {

	private final Model model;
	private final String stash;
	List<Instance> instances = new ArrayList<>();

	public Batch(Model model, String stash) {
		this.model = model;
		this.stash = stash;
	}

	public List<Concept> concepts() {
		return model.concepts();
	}

	public Concept conceptOf(String type) {
		return model.conceptOf(type);
	}

	public Concept conceptOf(Class<? extends Layer> layerClass) {
		return model.conceptOf(layerClass);
	}

	public List<Concept> mainConceptsOf(String type) {
		return model.mainConceptsOf(type);
	}

	public List<Concept> mainConceptsOf(Class<? extends Layer> layerClass) {
		return model.mainConceptsOf(layerClass);
	}

	public List<Concept> mainConceptsOf(Concept type) {
		return model.mainConceptsOf(type);
	}

	public Instance newMain(Concept concept) {
		return newMain(concept, model.newInstanceId());
	}

	public <T extends Layer> T newMain(Class<T> layerClass) {
		return newMain(layerClass, model.newInstanceId());
	}

	public Instance newMain(String type) {
		return newMain(conceptOf(type), model.newInstanceId());
	}

	public <T extends Layer> T newMain(Class<T> layerClass, String id) {
		Instance instance = newMain(conceptOf(layerClass), id);
		return instance != null ? instance.as(layerClass) : null;
	}

	public Instance newMain(String type, String id) {
		return newMain(conceptOf(type), id);
	}

	public Instance newMain(Concept concept, String id) {
		Instance instance = model.createInstance(concept, stash, id);
		instances.add(instance);
		return instance;
	}

	public void commit(){
		instances.forEach(model::commit);
		if(!instances.isEmpty()) model.save(instances.get(0));
	}
}
