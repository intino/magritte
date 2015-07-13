package magritte;

import siani.tara.magritte.Graph;
import siani.tara.magritte.editors.GraphEditor;
import siani.tara.magritte.schema.MemoryGraph;

import static siani.tara.magritte.Tag.*;
import static siani.tara.magritte.schema.Box.*;

public class Shop extends GraphEditor {

	private Shop(Graph graph) {
		super(graph);
	}

	public static Shop create() {
		return new Shop(new MemoryGraph());
	}

	public Graph m2() {
		def("Entity").type("Concept");
		def("Collection").type("Concept").parent("Entity");
		def("Form").type("Concept").parent("Entity").holds("Form.Field", "Form.Link");
		def("Form.Field").type("Concept");
		def("Form.Link").type("Concept");
		return graph;
	}

	private void m1_1() {
		m2();
		def("People|miyun").type("Collection").set(Main, Singleton).holds("Person");
		def("Catalog|zisvoros").type("Collection").set(Main, Singleton).holds("Product");
		def("Person|monjas").type("Form").set(Main, Abstract).requires("Person.FullName", "Person.Product").holds("Person.FullName", "Person.Phone");
		def("Person.FullName|flicmo").type("Form.Field").set("label", "Full name");
		def("Person.Phone|senut").type("Form.Field").set("label", "Phone").set("*value", "No phone");
		def("Person.Product|marmir").type("Form.Link").set("label", "Product");
		def("Person.Phone|senut").type("Form.Field").set("label", "Phone").set("*value", "No phone");
		def("Customer|jerlos").type("Form").set(Main).parent("Person");
		def("Provider|zutlu").type("Form").set(Main).parent("Person");
		def("Product|secre").type("Form").set(Main);
	}

	private void m1_2() {
		m2();
		def("Agenda|miyun").type("Collection").set(Root);
		def("Catalog|zisvoros").type("Collection").set(Root);
		def("Person|monjas").type("Form").set(Root, Abstract).requires("Person.Name", "Person.Product").holds("Person.Name", "Person.Phone");
		def("Person.Name|flicmo").type("Form.Field").set("label", "Full name");
		def("Person.Product|marmir").type("Form.Link").set("label", "Product");
		def("Person.Phone|senut").type("Form.Field").set("label", "Phone").set("*value", "No phone");
		def("Person.Photo|frocio").type("Form.Field").set("label", "Photo").set("*image", resource(in("person.png")));
		def("Person.Logo|portas").type("Form.Field").set("label", "Logo").set("*image", resource(in("logo.png")));
		def("Person.Date|grovus").type("Form.Field");
		def("Client|jerlos").type("Form").set(Root).parent("Person");
		def("Provider|zutlu").type("Form").set(Root).parent("Person");
		def("Product|secre").type("Form").set(Root).set("*image", resource(in("product.png")));
	}

	private void m1_3() {
		m2();
		def("Agenda|miyun").type("Collection").set(Root);
		def("Catalog|zisvoros").type("Collection").set(Root);
		def("Person|monjas").type("Form").set(Root, Abstract).requires("Person.Name", "Person.Product").holds("Person.Name", "Person.Phone");
		def("Person.Name|flicmo").type("Form.Field").set("label", "Full name");
		def("Person.Product|marmir").type("Form.Link").set("label", "Product");
		def("Person.Date|grovus").type("Form.Field");
		def("Client|jerlos").type("Form").set(Root).parent("Person");
		def("Provider|zutlu").type("Form").set(Root).parent("Person");
		def("Product|secre").type("Form").set(Root).set("*image", resource(in("product.png")));
	}

	private void m0_1() {
		m1_1();
		def("pleope").type("People").set(Case, Root);
		def("tacalon").type("Catalog").set(Case, Root);
	}

	private void m0_2() {
		m1_2();
		def("pleope").type("Agenda").set(Case, Root);
		def("tacalon").type("Catalog").set(Case, Root);
	}

	private void m0_3() {
		m1_3();
		def("pleope").type("Agenda").set(Case, Root);
		def("tacalon").type("Catalog").set(Case, Root);
	}

	public Graph m1(int version) {
		switch (version) {
			case 1:
				m1_1();
				break;
			case 2:
				m1_2();
				break;
			case 3:
				m1_3();
				break;
		}
		return graph;
	}

	public Graph m0(int version) {
		switch (version) {
			case 1:
				m0_1();
				break;
			case 2:
				m0_2();
				break;
			case 3:
				m0_3();
				break;
		}
		return graph;
	}

	public Graph m0() {
		m1(1);
		def("pleope").set(Case, Root).type("People").set("forms", multiple(ref("belyus"), ref("brorde"), ref("vibur"), ref("frelon"), ref("jarnat")));
		def("tacalon").set(Case, Root).type("Catalog").set("forms", multiple(ref("coffee"), ref("sugar"), ref("bag"), ref("ball")));
		def("belyus").set(Case, Root).type("Customer").has($(103, "Person.FullName"), $(104, "Person.Product"));
		def(103).set(Case).set("value", "Mario");
		def(104).set(Case).set("entities", multiple(ref("coffee"), ref("sugar")));

		def("brorde").set(Case, Root).type("Customer").has($(106, "Person.FullName"), $(107, "Person.Product"));
		def(106).set(Case).set("value", "Samuel");
		def(107).set(Case).set("entities", multiple(ref("sugar"), ref("bag")));

		def("vibur").set(Case, Root).type("Customer").has($(109, "Person.FullName"), $(110, "Person.Product"), $(111), $(112), $(113));
		def(109).set(Case).set("value", "Nacho");
		def(110).set(Case).set("entities", multiple(ref("bag")));
		def(111).set(Case).type("Person.Photo").set("image", resource(in("vibur.png")));
		def(112).set(Case).type("Person.Logo").set("image", resource(in("logo.png")));
		def(113).set(Case).type("Person.Date").set("value", date(2010, 10, 05));

		def("frelon").set(Case, Root).type("Provider").has($(114, "Person.FullName"), $(115, "Person.Product"));
		def(114).set(Case).set("value", "Jose");
		def(115).set(Case).set("entities", multiple(ref("coffee")));

		def("jarnat").set(Case, Root).type("Provider").has($(116, "Person.FullName"), $(117, "Person.Product"));
		def(116).set(Case).set("value", "Ruben");
		def(117).set(Case).set("entities", multiple(ref("ball")));

		def("coffee").set(Case, Root).type("Product").set("image", resource(in("coffee.jpg")));
		def("sugar").set(Case, Root).type("Product").set("image", resource(in("sugar.jpg")));
		def("bag").set(Case, Root).type("Product").set("image", resource(in("bag.jpg")));
		def("ball").set(Case, Root).type("Product").set("image", resource(in("ball.jpg")));
		return graph;
	}


	@Override
	public void write() {

	}


}
