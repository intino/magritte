package magritte.schema;

import magritte.Graph;
import magritte.Node;
import magritte.Reference;
import magritte.Source;
import magritte.editors.GraphEditor;
import magritte.editors.NodeEditor;
import magritte.primitives.Date;
import magritte.primitives.Resource;
import magritte.sources.BoxSource;

import java.util.ArrayList;
import java.util.List;

import static magritte.Tag.*;

public abstract class Box {

	private static final List<String> signatures = new ArrayList<>();

	protected Box() {
	}

	public final void load(Graph graph) {
		if (!canLoad(graph)) return;
		write(graph);
		signatures.add(signatureOf(graph));
	}

	protected boolean canLoad(Graph graph) {
		return !signatures.contains(signatureOf(graph));
	}

	private String signatureOf(Graph graph) {
		return graph.toString() + "." + this.getClass().getCanonicalName();
	}

	protected abstract void write(Graph graph);


	public static abstract class Dsl extends Box {

		protected abstract Box[] includes();

		@Override
		protected final void write(Graph graph) {
			for (Box include : includes()) include.load(graph);
		}
	}

	private static abstract class Unit extends Box {
		protected GraphEditor graphEditor;

		protected abstract Box[] dependencies();

		protected final void write(Graph graph) {
			graphEditor = modelEditor(graph);
			loadDependencies(graph);
			write();

		}

		private GraphEditor modelEditor(final Graph graph) {
			return new GraphEditor(graph) {
				@Override
				public void write() {
				}
			};
		}

		protected abstract void write();

		public NodeEditor def(int index) {
			return graphEditor.def(index);
		}

		public NodeEditor def(String name) {
			return graphEditor.def(name);
		}

		public Node $(int index) {
			return graphEditor.$(index);
		}

		public Reference ref(String name) {
			return graphEditor.ref(name);
		}

		private void loadDependencies(Graph graph) {
			for (Box box : dependencies()) box.load(graph);
		}


	}

	public static abstract class Ontology extends Unit {


	}

	public static abstract class Scene extends Unit {

		public Scene() {

		}

		public NodeEditor def(int index) {
			return graphEditor.def(index).set(Case);
		}

		public NodeEditor def(String name) {
			return graphEditor.def(name).set(Case);
		}

	}

	protected Box[] $(Box... boxes) {
		return boxes;
	}

	public static Source in(String path) {
		return BoxSource.in(path);
	}

	public static Resource resource(Source source) {
		return Resource.resource(source);
	}

	public static Date date(int... values) {
		return Date.date(values);
	}

	public static int[] multiple(int... values) {
		return values;
	}

	public static double[] multiple(double... values) {
		return values;
	}

	public static boolean[] multiple(boolean... values) {
		return values;
	}

	public static String[] multiple(String... values) {
		return values;
	}

	public static Date[] multiple(Date... values) {
		return values;
	}

	public static Resource[] multiple(Resource... values) {
		return values;
	}

	public static Reference[] multiple(Reference... values) {
		return values;
	}


}