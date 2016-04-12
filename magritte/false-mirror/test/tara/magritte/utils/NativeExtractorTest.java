package tara.magritte.utils;

import org.junit.Test;
import tara.magritte.Node;
import tara.magritte.Layer;
import tara.magritte.NativeCode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tara.magritte.utils.NativeExtractor.extract;

public class NativeExtractorTest {

	@Test
	public void should_provide_class_of_native() throws Exception {
		MockLayer mockLayer = new MockLayer(new Node());
		mockLayer.action(new ActionImpl());
		assertThat(extract("action", mockLayer).getClass().getName(), is("tara.magritte.utils.NativeExtractorTest$ActionImpl"));
	}

	static class MockLayer extends Layer {

		protected Action action;

		public MockLayer(Node _node) {
			super(_node);
		}

		public void action(Action action) {
			this.action = action;
		}

	}

	interface Action extends NativeCode {

		void execute();

	}

	static class ActionImpl implements Action {

		@Override
		public void execute() {
		}

		@Override
		public void self(Layer context) {
		}

		@Override
		public Class<? extends Layer> selfClass() {
			return null;
		}
	}

}