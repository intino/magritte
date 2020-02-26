package io.intino.magritte.framework.utils;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.Node;
import org.junit.Test;

import static io.intino.magritte.framework.utils.NativeExtractor.extract;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NativeExtractorTest {

	@SuppressWarnings("ConstantConditions")
	@Test
	public void should_provide_class_of_native() throws Exception {
		MockLayer mockLayer = new MockLayer(new Node());
		mockLayer.action(new ActionImpl());
		assertThat(extract("action", mockLayer).getClass().getName(), is("io.intino.magritte.framework.utils.NativeExtractorTest$ActionImpl"));
	}

	@SuppressWarnings("WeakerAccess")
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

		@SuppressWarnings("unused")
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