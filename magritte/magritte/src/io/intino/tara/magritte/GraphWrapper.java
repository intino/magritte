package io.intino.tara.magritte;

public abstract class GraphWrapper implements Cloneable {

	protected abstract void addNode$(Node node);

	protected abstract void removeNode$(Node node);

	protected abstract void update();

	public GraphWrapper clone() {
		try {
			return (GraphWrapper) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
