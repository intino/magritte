package io.intino.tara.magritte.utils;

import org.junit.Test;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static io.intino.tara.magritte.utils.NativeExtractor.extract;

public class NativeExtractorTest {

	@Test
	public void should_provide_class_of_native() throws Exception {
		MockLayer mockLayer = new MockLayer(new Node());
		mockLayer.action(new ActionImpl());
		assertThat(extract("action", mockLayer).getClass().getName(), is("NativeExtractorTest$ActionImpl"));
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