package io.intino.tara.magritte;

import java.util.List;

public class RemounterGraph extends Graph {

	public RemounterGraph(Store store) {
		super(store);
	}

	@Override
	public synchronized Graph loadStashes(String... paths) {
		return super.loadStashes(paths);
	}

	@Override
	public synchronized <T extends Layer> List<T> find(Class<T> aClass) {
		return super.find(aClass);
	}

	@Override
	public synchronized <T extends Layer> T createRoot(Class<T> layerClass) {
		return super.createRoot(layerClass);
	}

	@Override
	public synchronized Node createRoot(Concept concept, String stash) {
		return super.createRoot(concept, stash);
	}

	@Override
	public synchronized <T extends Layer> T createRoot(Class<T> layerClass, String namespace) {
		return super.createRoot(layerClass, namespace);
	}

	@Override
	public synchronized Node createRoot(String type, String namespace) {
		return super.createRoot(type, namespace);
	}

	@Override
	public synchronized <T extends Layer> T createRoot(Class<T> layerClass, String namespace, String name) {
		return super.createRoot(layerClass, namespace, name);
	}

	@Override
	public synchronized Node createRoot(Concept concept, String namespace, String name) {
		return super.createRoot(concept, namespace, name);
	}

	@Override
	public synchronized void remove(Node node) {
		super.remove(node);
	}

	@Override
	public synchronized Graph clone() {
		return this;
	}

	public synchronized RemounterGraph realClone() {
		return (RemounterGraph) GraphCloner.doClone(this, new RemounterGraph(this.store));
	}

}
