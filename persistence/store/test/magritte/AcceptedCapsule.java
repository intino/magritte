package magritte;

import magritte.schema.GateReference;
import magritte.schema.Gate;
import magritte.stores.MemoryStore;
import magritte.schema.Capsule;
import org.junit.Test;

import static magritte.Node.Member.Component;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedCapsule {

    @Test
    public void should_register_model_types() throws Exception {
        Capsule capsule = new Capsule(new MemoryStore()).with(m0());

        assertThat("Concept type exists", capsule.exists("Concept"));
        assertThat("People type exists", capsule.get("People") != null);
        assertThat("Customer type exists ", capsule.exists("Customer"));
        assertThat(capsule.get("Customer").toString(), is("Type[Customer:Form:Concept]"));
    }

    @Test
    public void should_register_roots_as_gates() throws Exception {
        Capsule capsule = new Capsule(new MemoryStore()).with(m0());

        assertThat(capsule.gates().size(), is(11));
        assertThat(capsule.gates().get(0).toString(), is("Gate[//miyun/pleope:People]"));
        assertThat(capsule.gates().get(1).toString(), is("Gate[//zisvoros/tacalon:Catalog]"));
        assertThat(capsule.gates().get(2).toString(), is("Gate[//jerlos/belyus:Customer]"));
        assertThat(capsule.gates().get(7).toString(), is("Gate[//secre/coffee:Product]"));
        assertThat(capsule.gates().get(8).toString(), is("Gate[//secre/sugar:Product]"));
        assertThat(capsule.get("pleope").toString(), is("Gate[//miyun/pleope:People]"));
        assertThat(capsule.get("belyus").toString(), is("Gate[//jerlos/belyus:Customer]"));
    }

    @Test
    public void should_save_gates_not_stored() throws Exception {
        Store store = new MemoryStore();
        store.save(GateReference.of("//secre/sugar"), new byte[]{1,2,3,4});
        new Capsule(store).with(m0());

        assertThat("", store.exists(GateReference.of("//miyun/pleope")));
        assertThat("", store.exists(GateReference.of("//jerlos/belyus")));
        assertThat(store.sourceOf(GateReference.of("//secre/sugar")).data(), is(new byte[]{1,2,3,4}));
    }

    @Test
    public void shold_load_a_singleton_gate() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0());

        assertThat(capsule.get("pleope").toString(), is("Gate[//miyun/pleope:People]"));
        Reference[] forms = capsule.get("pleope").get("forms");
        assertThat(capsule.get("pleope").toString(), is("Case[pleope:People:Collection:Concept]"));
        assertThat(capsule.gates().size(), is(11));
        assertThat(forms.length, is(5));
        assertThat(forms[0].toString(), is("//jerlos/belyus:Customer"));
        assertThat(forms[1].toString(), is("//jerlos/brorde:Customer"));
        assertThat(capsule.get("pleope").fanOut().size(), is(5));
        assertThat(capsule.get("pleope").fanOut().get(0).toString(), is("Gate[//jerlos/belyus:Customer]"));
    }

    //@Test
    public void shold_load_a_gate() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0());

        Node node = capsule.get("belyus");
        Reference[] entities = node.members(Component).get(1).get("entities");
        assertThat(entities[0].toString(), is("//secre/coffee:Product"));
        assertThat(entities[1].toString(), is("//secre/sugar:Product"));
        assertThat(node.members(Component).get(1).owner().type().title(), is("Customer"));
        assertThat(node.members(Component).get(1).get("entities"), is(new Reference[]{reference("//secre/coffee"), reference("//secre/sugar")}));
        assertThat(node.fanOut().size(), is(2));
        assertThat(node.fanOut().get(0).toString(), is("Gate[//secre/coffee:Product]"));
        assertThat(node.fanOut().get(1).toString(), is("Gate[//secre/sugar:Product]"));
        assertThat(node.fanOut().get(1).owner(), is(nullValue()));
        assertThat(node.fanOut().get(1).toString(), is("Case[sugar:Product:Form:Concept]"));
    }

    //@Test
    public void shold_load_gate_with_components_without_definition() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0());

        assertThat(capsule.get("vibur").toString(), is("Gate[//jerlos/vibur:Customer]"));
        assertThat(capsule.get("vibur").members(Component).size(), is(5));
        assertThat(capsule.get("vibur").members(Component).get(0).toString(), is("Case[?:Person.FullName:Form.Field:Concept]"));
        assertThat(capsule.get("vibur").members(Component).get(1).toString(), is("Case[?:Person.Product:Form.Link:Concept]"));
        assertThat(capsule.get("vibur").members(Component).get(2).toString(), is("Case[?:Person.Photo]"));
        assertThat(capsule.get("vibur").members(Component).get(3).toString(), is("Case[?:Person.Logo]"));
        assertThat(capsule.get("vibur").members(Component).get(4).toString(), is("Case[?:Person.Date]"));
    }

    //@Test
    public void shold_load_gates() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0()).load();


        assertThat(capsule.gates().size(), is(11));
        Node node = capsule.get("belyus");
        assertThat("Node is gate", node instanceof Gate);
        assertThat("Node is not dirty", !node.as(Gate.class).dirty());
        assertThat(node.owner(), is(nullValue()));
        assertThat(node.toString(), is("Case[belyus:Customer:Form:Concept]"));
        assertThat("Node should have a single type", node.types().size() == 1);
        assertThat("Node should be a Customer", node.type() == capsule.get("Customer"));
        assertThat("Node should have a single type", node.types().size() == 1);
        assertThat("Node should be a Customer", node.type() == capsule.get("Customer"));
        assertThat(node.members(Component).size(), is(2));
        assertThat(node.title(), is("belyus"));
        assertThat(node.members(Component).get(0).title(), is("?"));
        assertThat(node.members(Component).get(0).name(), is(nullValue()));
        assertThat(node.members(Component).get(0).vars().length, is(2));
        assertThat(node.members(Component).get(0).<String>get("value"), is("Mario"));
        assertThat(node.members(Component).get(0).type().title(), is("Person.FullName"));
        assertThat(node.members(Component).get(0).owner().type().title(), is("Customer"));
        assertThat(node.members(Component).get(0).title(), is("?"));
        assertThat(node.members(Component).get(0).vars().length, is(2));
        assertThat(node.members(Component).get(0).type().title(), is("Person.FullName"));
        assertThat(node.members(Component).get(1).type().title(), is("Person.Product"));
    }

    @Test
    public void should_mark_as_dirty_a_modified_node() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0());

        Node node = capsule.get("belyus");
        assertThat("Node is not dirty", !node.as(Gate.class).dirty());
        node.set("any", "Modified");
        assertThat("Node is dirty", node.as(Gate.class).dirty());
    }

    @Test
    public void should_save_dirty_node() throws Exception {
        Capsule capsule = new Capsule(edit(store())).with(m0());
        Node node = capsule.get("belyus");
        assertThat("Node is not dirty", !node.as(Gate.class).dirty());
        assertThat(node.get("any"), is("Modified"));

    }

    @Test
    public void should_open_uid() throws Exception {
        GateReference uid = GateReference.of("//jerlos/belyus:Customer");
        Capsule capsule = new Capsule(store()).with(m0()).open(uid);

        assertThat(capsule.gates().size(), is(1));
        assertThat(capsule.gates().get(0).toString(), is("Gate[//jerlos/belyus:Customer]"));
        assertThat(capsule.gates().get(0).load().toString(), is("Case[belyus:Customer:Form:Concept]"));
    }

    @Test
    public void should_open_string_uid() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0()).open("//jerlos/belyus:Customer");

        assertThat(capsule.gates().size(), is(1));
        assertThat(capsule.gates().get(0).toString(), is("Gate[//jerlos/belyus:Customer]"));
        assertThat(capsule.gates().get(0).load().toString(), is("Case[belyus:Customer:Form:Concept]"));
    }

//    @Test
    public void should_load_nodes_stored_with_different_types() throws Exception {
        Capsule capsule = new Capsule(store()).with(m0(2));

        assertThat(capsule.gates().size(), is(2));
        assertThat(capsule.gates().get(0).toString(), is("Gate[//miyun/pleope:Agenda]"));
        Node node = capsule.get("pleope");
        assertThat(node.toString(), is("Gate[//miyun/pleope:Agenda]"));
        assertThat(node.as(Gate.class).load().toString(), is("Case[pleope:Agenda:Collection:Concept]"));
        assertThat(node.fanOut().size(), is(5));
        assertThat(node.fanOut().get(0).toString(), is("Gate[//jerlos/belyus:Customer]"));
        assertThat(node.fanOut().get(0).members(Component).size(), is(2));
        assertThat(node.fanOut().get(0).members(Component).get(0).toString(), is("Case[?:Person.Name:Form.Field:Concept]"));
        assertThat(node.fanOut().get(0).members(Component).get(1).toString(), is("Case[?:Person.Product:Form.Link:Concept]"));
        assertThat(node.fanOut().get(2).toString(), is("Gate[//jerlos/vibur:Customer]"));
        assertThat(node.fanOut().get(2).members(Component).size(), is(5));
        assertThat(node.fanOut().get(2).members(Component).get(0).toString(), is("Case[?:Person.Name:Form.Field:Concept]"));
        assertThat(node.fanOut().get(2).members(Component).get(1).toString(), is("Case[?:Person.Product:Form.Link:Concept]"));
        assertThat(node.fanOut().get(2).members(Component).get(2).toString(), is("Case[?:Person.Photo:Form.Field:Concept]"));
        assertThat(node.fanOut().get(2).members(Component).get(3).toString(), is("Case[?:Person.Logo:Form.Field:Concept]"));
        assertThat(node.fanOut().get(2).members(Component).get(4).toString(), is("Case[?:Person.Date:Form.Field:Concept]"));
    }
    

    private Store store() {
        Store store = new MemoryStore();
        new Capsule(store).with(m0());
        return store;
    }

    private Store edit(Store store) {
        Capsule capsule = new Capsule(store).with(m0());
        capsule.get("belyus").set("any", "Modified");
        capsule.save();
        return capsule.store();
    }

    private Graph m0(int version) {
        return Shop.create().m0(version);
    }

    private Graph m0() {
        return Shop.create().m0();
    }

    private Reference reference(String ref) {
        return GateReference.of(ref);
    }



}
