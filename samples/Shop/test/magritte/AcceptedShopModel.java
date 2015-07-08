package magritte;

import bazar.*;
import siani.tara.magritte.Graph;
import siani.tara.magritte.Reference;
import siani.tara.magritte.primitives.Resource;
import org.junit.Test;
import shop.Collection;
import shop.Entity;
import shop.Form;
import shop.UppercaseField;

import static siani.tara.magritte.Node.Member.Component;
import static siani.tara.magritte.helpers.Extract.nameOf;
import static siani.tara.magritte.helpers.Selection.instancesOf;
import static siani.tara.magritte.wraps.Operation.Add;
import static siani.tara.magritte.wraps.Operation.Set;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AcceptedShopModel {

	@Test
	public void should_load_m2() throws Exception {
		Graph graph = Shop.create().m2();

		assertThat(graph.roots().size(), is(0));
		assertThat(graph.get("Form").type(), is(graph.get("Concept")));
		assertThat(graph.get("Form").type(), is(graph.get("Concept")));
		assertThat(graph.get("Form").parent(), is(graph.get("Entity")));
		assertThat(graph.get("Collection").parent(), is(graph.get("Entity")));
		assertThat(graph.get("Form").types().size(), is(1));
		assertThat(graph.get("Collection").types().size(), is(1));
	}

	@Test
	public void should_load_m1() throws Exception {
		Graph graph = Shop.create().m1(1);

		assertThat(graph.roots().size(), is(0));
		assertThat(graph.get("Person").type(), is(graph.get("Form")));
		assertThat(graph.get("Customer").parent(), is(graph.get("Person")));
		assertThat(graph.get("Provider").parent(), is(graph.get("Person")));
		assertThat(graph.get("Person").toString(), is("Type[Person:Form:Concept]"));
		assertThat(graph.get("Customer").toString(), is("Type[Customer:Form:Concept]"));
		assertThat(graph.get("Provider").toString(), is("Type[Provider:Form:Concept]"));
		assertThat(graph.get("Catalog").toString(), is("Type[Catalog:Collection:Concept]"));
		assertThat(graph.get("Product").toString(), is("Type[Product:Form:Concept]"));
		assertThat(graph.get("People").toString(), is("Type[People:Collection:Concept]"));
		assertThat(graph.get("miyun").toString(), is("Type[People:Collection:Concept]"));
		assertThat(graph.get("People").name(), is("People|miyun"));
		assertThat(graph.get("People").title(), is("People"));
		assertThat(graph.get("miyun").name(), is("People|miyun"));
		assertThat(graph.get("miyun").title(), is("People"));
	}

	@Test
	public void should_contain_resources() throws Exception {
		Graph graph = Shop.create().m0();
		Resource images[] = new Resource[6];
		images[0] = defaultImage();
		images[1] = graph.get("coffee").get("image");
		images[2] = graph.get("sugar").get("image");
		images[3] = graph.get("bag").get("image");
		images[4] = graph.get("ball").get("image");
		images[5] = graph.get("vibur").members(Component).get(2).get("image");

		assertThat(images[0].format(), is("png"));
		assertThat(images[0].size(), is(3641));
		assertThat(images[1].format(), is("jpg"));
		assertThat(images[1].size(), is(6117));
		assertThat(images[2].format(), is("jpg"));
		assertThat(images[2].size(), is(3245));
		assertThat(images[3].format(), is("jpg"));
		assertThat(images[3].size(), is(4352));
		assertThat(images[4].format(), is("jpg"));
		assertThat(images[4].size(), is(5370));
		assertThat(images[5].format(), is("png"));
		assertThat(images[5].size(), is(33186));
	}

	@Test
	public void should_contain_nodes_indexed_by_name() throws Exception {
		Graph graph = Shop.create().m0();

		assertThat(graph.get("People").name(), is("People|miyun"));
		assertThat(graph.get("People").title(), is("People"));
		assertThat(graph.get("People").key(), is("miyun"));
		assertThat(graph.get("miyun").name(), is("People|miyun"));
		assertThat(graph.get("miyun").key(), is("miyun"));
		assertThat(graph.get("miyun").title(), is("People"));
		assertThat(graph.get("pleope").toString(), is("Case[pleope:People:Collection:Concept]"));
		assertThat(graph.get("pleope").name(), is("pleope"));
		assertThat(graph.get("pleope").title(), is("pleope"));
		assertThat(graph.get("pleope").members(Component).size(), is(0));
		assertThat(graph.get("pleope").<Reference[]>get("forms").length, is(5));
		assertThat(graph.get("belyus").toString(), is("Case[belyus:Customer:Form:Concept]"));
		assertThat(graph.get("belyus").members(Component).get(0).get("value"), is("Mario"));
		assertThat(graph.get("belyus").members(Component).get(1).toString(), is("Case[?:Person.Product:Form.Link:Concept]"));
		assertThat(graph.get("belyus").members(Component).get(1).<Reference[]>get("entities").length, is(2));
		assertThat(graph.get("coffee").toString(), is("Case[coffee:Product:Form:Concept]"));
		assertThat(graph.get("coffee").fanIn().size(), is(3));
		assertThat(graph.get("coffee").fanIn().get(2).toString(), is("Case[frelon:Provider:Form:Concept]"));
		assertThat(graph.get("frelon").fanOut().size(), is(1));
		assertThat(graph.get("frelon").fanOut().get(0).name(), is("coffee"));

		assertThat(graph.roots().size(), is(11));
		assertThat(graph.roots().filter(instancesOf(nameOf(Form.class))).size(), is(9));
		assertThat(graph.roots().filter(instancesOf(nameOf(Collection.class))).size(), is(2));
		assertThat(graph.roots().filter(instancesOf(nameOf(Entity.class))).size(), is(11));
		assertThat(graph.roots().filter(instancesOf(nameOf(Customer.class))).size(), is(3));
		assertThat(graph.roots().filter(instancesOf(nameOf(Provider.class))).size(), is(2));
		assertThat(graph.roots().filter(instancesOf(nameOf(Product.class))).size(), is(4));
		assertThat(graph.roots().filter(instancesOf(nameOf(People.class))).size(), is(1));
		assertThat(graph.roots().filter(instancesOf(nameOf(Catalog.class))).size(), is(1));

	}

	@Test
	public void should_contain_nodes_without_type() throws Exception {
		Graph graph = Shop.create().m0();

		assertThat(graph.get("vibur").members(Component).size(), is(5));
		assertThat(graph.get("vibur").members(Component).get(0).toString(), is("Case[?:Person.FullName:Form.Field:Concept]"));
		assertThat(graph.get("vibur").members(Component).get(1).toString(), is("Case[?:Person.Product:Form.Link:Concept]"));
		assertThat(graph.get("vibur").members(Component).get(2).toString(), is("Case[?:Person.Photo]"));
		assertThat(graph.get("vibur").members(Component).get(3).toString(), is("Case[?:Person.Logo]"));
		assertThat(graph.get("vibur").members(Component).get(4).toString(), is("Case[?:Person.Date]"));
	}


	@Test
	public void should_be_used_with_m2_morphs() throws Exception {
		Graph graph = Shop.create().m0();
		shop.Shop shop = new shop.Shop(graph);

		assertThat(shop.collectionSet().size(), is(2));
		assertThat(shop.formSet().size(), is(9));
		assertThat(shop.collection(0).forms().size(), is(5));
		assertThat(shop.collection(0).forms_(0)._node().toString(), is("Case[belyus:Customer:Form:Concept]"));
		assertThat(shop.collection(0).forms_(1)._node().toString(), is("Case[brorde:Customer:Form:Concept]"));

		assertThat(shop.collection(0).forms_(1).toString(), is("Form of Case[brorde:Customer:Form:Concept]"));
		assertThat(shop.collection(0).forms_(1).field(0).label(), is("Full name"));
		assertThat(shop.collection(0).forms_(1).field(0).value(), is("Samuel"));
		assertThat(shop.collection(0).forms_(1).field(0).as(UppercaseField.class).value(), is("SAMUEL"));
		assertThat(shop.collection(0).forms_(1)._references().size(), is(2));
		assertThat(shop.collection(0).forms_(1)._reference(0).toString(), is("Morph of Case[sugar:Product:Form:Concept]"));
		assertThat(shop.collection(0).forms_(1)._reference(1).toString(), is("Morph of Case[bag:Product:Form:Concept]"));
		assertThat("Form is customer", shop.collection(0).forms_(1).is("Customer"));

		assertThat(shop.collection(1).forms().size(), is(4));
		assertThat("Form is a product", shop.collection(1).forms_(0).is("Product"));
		assertThat("Form is a product", shop.collection(1).forms_(1).is("Product"));
		assertThat(shop.collection(1).forms_(0)._name(), is("coffee"));
		assertThat(shop.collection(1).forms_(0)._referees().size(), is(3));
		assertThat(shop.collection(1).forms_(0)._referee(0)._name(), is("tacalon"));
		assertThat(shop.collection(1).forms_(0)._referee(1)._name(), is("belyus"));
		assertThat(shop.collection(1).forms_(0)._referee(2)._name(), is("frelon"));
	}

	@Test
	public void should_be_used_with_m1_morphs() throws Exception {
		Graph graph = Shop.create().m0();
		Bazar bazar = new Bazar(graph);
		People people = bazar.people();
		Catalog catalog = bazar.catalog();

		assertThat(people.forms().size(), is(5));
		assertThat(people.forms_(0).fullNameSet().get(0).value(), is("Mario"));
		assertThat(people.forms_(1).fullNameSet().get(0).value(), is("Samuel"));
		assertThat(people.forms_(2).fullNameSet().get(0).value(), is("Nacho"));
		assertThat(catalog.forms().size(), is(4));
		assertThat(catalog.forms_(0)._name(), is("coffee"));
		assertThat(catalog.forms_(1)._name(), is("sugar"));
		assertThat(catalog.forms_(2)._name(), is("bag"));
		assertThat(catalog.forms_(3)._name(), is("ball"));
		assertThat(catalog.forms_(0)._referees().size(), is(3));
		assertThat(catalog.forms_(0)._referee(1)._name(), is("belyus"));
		assertThat(catalog.forms_(1)._referees().size(), is(3));
		assertThat(catalog.forms_(1)._referee(1)._name(), is("belyus"));
		assertThat(catalog.forms_(3)._referees().size(), is(2));
		assertThat(catalog.forms_(3)._referee(1)._name(), is("jarnat"));

	}

	@Test
	public void should_be_edited_with_m1_morphs_and_queried_with_m2_morphs() throws Exception {
		Graph graph = Shop.create().m0();
		shop.Shop shop = new shop.Shop(graph);
		Bazar bazar = new Bazar(graph);
		People people = bazar.people();
		Catalog catalog = bazar.catalog();
		Collection peopleCollection = shop.collection(0);
		Collection catalogCollection = shop.collection(1);

		assertThat(peopleCollection.forms().size(), is(5));
		assertThat(catalogCollection.forms().size(), is(4));

		assertThat(peopleCollection.forms_(0).link(0).entities().size(), is(2));
		assertThat(peopleCollection.forms().size(), is(5));
		assertThat(people.forms_(0).product().entities().size(), is(2));

		people.forms_(0).fullNameSet().get(0).value("Mario Caballero");
		people.forms_(0).product().entities(Add, catalog.forms_(3));
		assertThat(people.forms_(0).product().entities().size(), is(3));

		assertThat(peopleCollection.forms_(0).field(0).value(), is("Mario Caballero"));
		assertThat(peopleCollection.forms_(0).link(0).entities().size(), is(3));
		assertThat(peopleCollection.forms_(0).link(0).entities_(2).toString(), is("Entity of Case[ball:Product:Form:Concept]"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).size(), is(3));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(0).toString(), is("Entity of Case[coffee:Product:Form:Concept]"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(1).toString(), is("Entity of Case[sugar:Product:Form:Concept]"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(2).toString(), is("Entity of Case[ball:Product:Form:Concept]"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(2)._referees().size(), is(3));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(2)._referee(0)._name(), is("tacalon"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(2)._referee(1)._name(), is("jarnat"));
		assertThat(peopleCollection.forms_(0)._references(Entity.class).get(2)._referee(2)._name(), is("belyus"));


		Product product = bazar._createProduct();
		product._name("ciagro");
		catalog.forms(Add, product);

		Customer customer = bazar._createCustomer();
		customer.fullNameSet().get(0).value("Jose Juan");
		customer.product().entities(Set, product);
		customer._name("jj");
		customer._create(Person.FullName.class);
		customer.fullNameSet().get(1).value("Jose J.");
		customer._create(Person.Phone.class);
		people.forms(Add, customer);

		assertThat(peopleCollection.forms().size(), is(6));
		assertThat(shop.formSet().size(), is(11));
		assertThat(shop.collectionSet().size(), is(2));

		assertThat(customer.fullNameSet().size(), is(2));
		assertThat(customer.fullNameSet().get(0)._node().toString(), is("Case[?:Person.FullName:Form.Field:Concept]"));
		assertThat(customer.fullNameSet().get(1)._node().toString(), is("Case[?:Person.FullName:Form.Field:Concept]"));
		assertThat(customer.phoneSet().size(), is(1));
		assertThat(customer.phoneSet().get(0)._node().toString(), is("Case[?:Person.Phone:Form.Field:Concept]"));
		assertThat(customer.product()._node().toString(), is("Case[?:Person.Product:Form.Link:Concept]"));

		assertThat(customer.fullNameSet().size(), is(2));
		assertThat(customer.fullNameSet().get(0).value(), is("Jose Juan"));
		assertThat(customer.fullNameSet().get(1).value(), is("Jose J."));
		assertThat(customer.phoneSet().size(), is(1));
		assertThat(customer.phoneSet().get(0).value(), is("No phone"));

		assertThat(peopleCollection.forms_(5)._name(), is("jj"));
		assertThat(peopleCollection.forms_(5).field(0).value(), is("Jose Juan"));
		assertThat(peopleCollection.forms_(5).field(1).value(), is("Jose J."));
	}

	private Resource defaultImage() {
		return Shop.create().m1(2).get("Product").get("*image");
	}


}
