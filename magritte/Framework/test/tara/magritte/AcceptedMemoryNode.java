package tara.magritte;

import tara.magritte.Node;
import tara.magritte.Tag;
import tara.magritte.editors.NodeEditor;
import tara.magritte.schema.MemoryGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static tara.magritte.Node.Member.Aggregable;
import static tara.magritte.Node.Member.Component;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AcceptedMemoryNode {

	private MemoryGraph model;

	@Before
	public void init() {
		this.model = new MemoryGraph();
	}

	@Test
	public void should_return_title_from_name() throws Exception {
		assertThat(createType("Phone").name(), is("Phone"));
		assertThat(createType("Phone").title(), is("Phone"));
		assertThat(createType("Phone").key(), is("Phone"));
		assertThat(createType("Phone|saso12").name(), is("Phone|saso12"));
		assertThat(createType("Phone|saso12").title(), is("Phone"));
		assertThat(createType("Phone|saso12").key(), is("saso12"));
		assertThat(createType("saso12").name(), is("saso12"));
		assertThat(createType("saso12").title(), is("saso12"));
		assertThat(createType("saso12").key(), is("saso12"));
		assertThat(create().name(), is(nullValue()));
		assertThat(create().title(), is("?"));
		assertThat(create().key(), is("?"));
	}

	@Test
	public void should_be_able_to_register_child_and_parent_links() throws Exception {
		Node parent = createType("Parent");
		Node child1 = createType("Child1");
		Node child2 = createType("Child2");

		new NodeEditor(child1).parent(parent);
		new NodeEditor(child2).parent(parent);

		assertThat(parent.children().size(), is(2));
		assertThat(parent.children().get(0), is(child1));
		assertThat(parent.children().get(1), is(child2));
		assertThat("Parent contains child", parent.children().contains(child2), is(true));
		assertThat(child1.parent(), is(parent));
		assertThat(child2.parent(), is(parent));
	}

	@Test
	public void should_register_members() throws Exception {
		Node owner = createType("Owner");
		Node owned = createType("Owned");
		Node aggregable = createType("Aggregable");
		new NodeEditor(owner).has(owned);
		new NodeEditor(owner).holds(aggregable);

		assertThat(owner.members(Component).size(), is(1));
		assertThat(owner.members(Component).get(0), is(owned));
		assertThat(owner.members(Aggregable).size(), is(1));
		assertThat(owner.members(Aggregable).get(0), is(aggregable));
		assertThat(owned.owner(), is(owner));
		assertThat(aggregable.owner(), is(owner));
	}

	@Test
	public void should_iterate_members() throws Exception {
		Node owner = createType("Owner");
		Node owned1 = createType("Owned");
		Node owned2 = createType("Owned");
		new NodeEditor(owner).has(owned1);
		new NodeEditor(owner).has(owned2);

		List<String> result = new ArrayList<>();
		owner.members(Component).forEach(item -> result.add(item.name()));
		assertThat(result.size(), is(2));
	}

	@Test
	public void should_remove_members() throws Exception {
		Node owner = createType("Owner");
		Node owned = createType("Owned");

		new NodeEditor(owner).has(owned);
		new NodeEditor(owner).remove(owned);

		assertThat(owner.members(Component).size(), is(0));
		assertNull(owned.owner());
	}

//    @Test
//    public void types() throws Exception {
//        Node thermal = createType("Thermal|000");
//        Node electrical = createType("Electrical|001");
//        Node radiator = createType("Radiator|002");
//        Node appliance = createType("Appliance|003");
//        Node device = createType("Device|004");
//
//        new NodeEditor(radiator).parent(appliance);
//        new NodeEditor(appliance).parent(device);
//
//        Node instance = create("Instance|100");
//        new NodeEditor(instance).type(radiator).type(thermal).type(electrical);
//        new NodeEditor(instance).type(thermal);
//        assertThat(instance.title(), is("Instance"));
//        assertThat(instance.type().title(), is("Radiator"));
//        assertThat(instance.toString(), is("Case[Instance:Radiator]"));
//        assertThat(instance.types().size(), is(3));
//        assertThat(instance.types().get(0), is(radiator));
//        assertThat(instance.types().get(1), is(thermal));
//        assertThat(instance.types().get(2), is(electrical));
//        assertThat(check(instance).isInstanceOf(thermal), is(true));
//        assertThat(check(instance).isInstanceOf(electrical), is(true));
//        assertThat(check(instance).isInstanceOf(radiator), is(true));
//        assertThat(check(instance).isInstanceOf(appliance), is(true));
//        assertThat(check(instance).isInstanceOf(device), is(true));
//    }


	public Node createType(String name) {
		Node node = model.createNode();
		node.set(name);
		return node;
	}

	public Node create(String name) {
		Node node = model.createNode();
		node.set(name);
		node.set(Tag.Case);
		return node;
	}

	public Node create() {
		Node node = model.createNode();
		node.set(Tag.Case);
		return node;
	}

}
