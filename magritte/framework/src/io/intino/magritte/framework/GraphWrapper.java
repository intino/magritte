package io.intino.magritte.framework;

public abstract class GraphWrapper implements Cloneable {

	protected abstract void addNode$(Node node);

	protected abstract void removeNode$(Node node);

	protected abstract void update();

}
