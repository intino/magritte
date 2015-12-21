package tara.magritte.utils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnchorHandlerTest {

	AnchorHandler handler;

	@Before
	public void setUp() throws Exception {
		handler = new AnchorHandler(AnchorHandlerTest.class.getResource("/.anchors"));
	}

	@Test
	public void being_in_build_1_should_provide_member_when_asked_by_entity_in_build_2_and_3() throws Exception {
		assertThat(handler.anchorOf("Entity", "1"), is("asdasfasdfa"));
		assertThat(handler.conceptOf("asdasfasdfa", "1"), is("Entity"));
		assertThat(handler.conceptOf(handler.anchorOf("Entity", "1"), "2"), is("Member"));
		assertThat(handler.conceptOf(handler.anchorOf("Entity", "1"), "3"), is("Member"));
		assertThat(handler.last(handler.anchorOf("Entity", "1")), is("Member"));
	}

	@Test
	public void should_provide_behavior_in_build_1_and_2_and_aspect_in_build_3() throws Exception {
		assertThat(handler.anchorOf("Behavior", "1"), is("sadfasdfsaa"));
		assertThat(handler.conceptOf("sadfasdfsaa", "1"), is("Behavior"));
		assertThat(handler.conceptOf(handler.anchorOf("Behavior", "1"), "2"), is("Behavior"));
		assertThat(handler.conceptOf(handler.anchorOf("Behavior", "1"), "3"), is("Aspect"));
		assertThat(handler.last(handler.anchorOf("Behavior", "1")), is("Aspect"));
	}
}