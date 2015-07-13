package monet;

import tara.magritte.Set;
import tara.magritte.wraps.Definition;
import tara.magritte.wraps.Morph;
import monet.natives.Action;

public class Entity extends Morph {

	public Toolbar toolbar() {
		return _definition()._component(Toolbar.class);
	}

	public static class Toolbar extends Definition {

		public Set<Operation> operationSet() {
			return _components(Operation.class);
		}

		public Operation operation(int index) {
			return operationSet().get(index);
		}

		public static class Operation extends Definition {

			public String label() {
				return _get("label").asString();
			}

			public void action() {
				Action action = _get("action").asNative();
				action.execute();
			}


		}


	}

}
