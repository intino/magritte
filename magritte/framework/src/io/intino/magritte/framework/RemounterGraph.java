package io.intino.magritte.framework;

import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
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

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	public synchronized Graph clone() {
		return this;
	}

	@Override
	void save(Node node) {
		save(node.stash());
	}

	@Override
	public void save(String... stashes) {
		if (!store.allowWriting()) return;
		super.save(stashes);
	}

	@Override
	public void saveAll(String... excludedStashes) {
		if (!store.allowWriting()) return;
		super.saveAll(excludedStashes);
	}

	public synchronized RemounterGraph realClone() {
		return (RemounterGraph) GraphCloner.doClone(this, new RemounterGraph(this.store));
	}

}
