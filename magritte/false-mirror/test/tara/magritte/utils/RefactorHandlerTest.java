package tara.magritte.utils;

import org.junit.Before;
import org.junit.Test;
import tara.io.refactor.Refactors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RefactorHandlerTest {

	RefactorHandler handler;

	@Before
	public void setUp() throws Exception {
		handler = new RefactorHandler(getRefactors());
	}

	@Test
	public void being_in_build_0_should_provide_thing_when_asked_by_entity() throws Exception {
		assertThat(handler.last("Entity", 0), is("Thing"));
	}

	@Test
	public void should_provide_aspect() throws Exception {
		assertThat(handler.last("Behavior", 0), is("Aspect"));
	}

	private Refactors getRefactors() {
		Refactors refactors = new Refactors();
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Entity", "Member"));
		refactors.add(new Refactors.Refactor("asdasdsadaaz", "Behavior", "Aspect"));
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Member", "Thing"));
		return refactors;
	}
}