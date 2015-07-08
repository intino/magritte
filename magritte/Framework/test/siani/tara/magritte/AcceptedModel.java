package siani.tara.magritte;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class AcceptedModel {

	@Test
	public void should_return_stored_case_node() throws Exception {
		Graph graph = SimpleProject.start().singleConcept().graph();
		assertThat(graph.roots().size(), is(1));
		assertThat(graph.get("Thing"), is(nullValue()));
		assertThat(graph.get("thing"), is(not(nullValue())));
		assertThat(graph.get("thing").title(), is("thing"));
		assertThat(graph.get("thing").toString(), is("Case[thing:Concept]"));
	}

	@Test
	public void should_return_stored_type_and_case_nodes() throws Exception {
		Graph graph = SimpleProject.start().singleConceptWithType().graph();

		assertThat(graph.roots().size(), is(1));
		assertThat(graph.get("Non existing object"), is(nullValue()));
		assertThat(graph.get("Class"), is(not(nullValue())));
		assertThat(graph.get("100"), is(not(nullValue())));
		assertThat("Return the same node", graph.get("Class") == graph.get("100"));
		assertThat(graph.get("thing"), is(not(nullValue())));
		assertThat(graph.get("thing").type(), is(not(nullValue())));
		assertThat(graph.get("thing").toString(), is("Case[thing:Class:Concept]"));
		assertThat(graph.get("thing").title(), is("thing"));
		assertThat(graph.get("thing").type().title(), is("Class"));
	}


}
