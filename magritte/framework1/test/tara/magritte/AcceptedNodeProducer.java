package tara.magritte;

import org.junit.Test;

import static tara.magritte.Node.Member.Component;
import static tara.magritte.Tag.Case;
import static tara.magritte.Tag.Root;
import static tara.magritte.handlers.NodeProducer.produce;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class AcceptedNodeProducer {

	@Test
	public void should_produce_node_with_default_variables() throws Exception {
		Graph graph = SimpleProject.start().typeWithDefaultVars().graph();
		Node node = produce(graph.createNode()).with(graph.get("Class")).node();
		assertThat("Node is Case", node.is(Case));
		assertThat("Node is not Root", !node.is(Root));
		assertThat(node.type().name(), is("Class"));
		assertThat(node.name(), is(nullValue()));
		assertThat(node.vars().length, is(3));
		assertThat(node.get("a"), is(1));
		assertThat(node.get("b"), is("text"));
		assertThat(node.members(Component).size(), is(0));
	}

	@Test
	public void should_produce_root_node() throws Exception {
		Graph graph = SimpleProject.start().mainType().graph();
		Node node = produce(graph.createNode()).with(graph.get("Class")).node();
		assertThat("Node is Root", node.is(Root));
		assertThat(node.type().name(), is("Class"));
		assertThat(node.members(Component).size(), is(0));
		assertThat(node.vars().length, is(1));
	}

	@Test
	public void should_produce_root_node_with_required_components() throws Exception {
		Graph graph = SimpleProject.start().mainTypeWithRequiredMembers().graph();
		Node node = produce(graph.createNode()).with(graph.get("Container")).node();
		assertThat("Node is Root", node.is(Root));
		assertThat(node.type().name(), is("Container"));
		assertThat(node.members(Component).size(), is(1));
		assertThat(node.members(Component).get(0).type().name(), is("Component"));
		assertThat(node.members(Component).get(0).members(Component).size(), is(0));
		assertThat(node.vars().length, is(1));
	}

	@Test
	public void should_produce_root_node_with_case() throws Exception {
		Graph graph = SimpleProject.start().mainTypeWithCase().graph();
		Node node = produce(graph.createNode()).with(graph.get("Container")).node();
		assertThat("Node is Root", node.is(Root));
		assertThat(node.members(Component).size(), is(0));
		assertThat(node.vars().length, is(1));
	}

	@Test
	public void should_produce_root_node_with_prototype() throws Exception {
		Graph graph = SimpleProject.start().mainTypeWithPrototype().graph();
		Node node = produce(graph.createNode()).with(graph.get("Container")).node();
		assertThat(graph.get("Container").members(Component).get(0).toString(), is("Case[?:Concept]"));
		assertThat("Node is Root", node.is(Root));
		assertThat(node.members(Component).size(), is(1));
		assertThat(node.members(Component).get(0).toString(), is("Case[?:Concept]"));
		assertThat("Prototype is cloned", node.members(Component).get(0) != graph.get("Container").members(Component).get(0));
		assertThat(node.vars().length, is(1));
	}


}
