package magritte.ontology;

import tara.magritte.schema.Box;

import static tara.magritte.Tag.Abstract;
import static tara.magritte.Tag.Facet;

public class MonetMain extends Box.Ontology {
	public static final Box box = new MonetMain();

	@Override
	public Box[] dependencies() {
		return $();
	}

	@Override
	public void write() {
		def("Entity").type("Concept").set(Abstract).allows("Entity.Toolbar");
		def("Entity.Toolbar").type("Concept").holds("Entity.Toolbar.Operation");
		def("Entity.Toolbar.Operation").type("Concept");

		def("Form").type("Concept").parent("Entity").requires("Field").holds("Field");
		def("Collection").type("Concept").parent("Entity").holds("Collection.Entry");
		def("Collection.Entry").type("Concept");
		def("Thesaurus").type("Concept").parent("Entity").holds("Thesaurus.Term");
		def("Thesaurus.Term").type("Concept").holds("Thesaurus.Term");

		def("Field").type("Concept").set(Abstract);
		def("Text").type("Concept").parent("Field");
		def("Serie").type("Concept").parent("Field");
		def("Boolean").type("Concept").parent("Field");
		def("Number").type("Concept").parent("Field");
		def("Date").type("Concept").parent("Field");
		def("Select").type("Concept").parent("Field");
		def("Picture").type("Concept").parent("Field");
		def("Composite").type("Concept").parent("Field").requires("Field").holds("Field");
		def("Multiple").type("Concept").parent("Field").requires("Field").set("*min", 0).set("*max", -1);

		def("Editable").type("Concept").set(Facet, Abstract);
		def("Editable.Permission").type("Concept");

		def("Editable+Picture").type("Concept").parent("Editable").set(Facet).
			holds("Editable.Permission").requires("Edition+Picture.Format").
			set("*Editable+label", "edition");
		def("Editable+Picture.Format").type("Concept");

		def("Editable+Multiple").type("Concept").parent("Editable").set(Facet).
			holds("Editable.Permission").requires("Editable+Multiple.Size").
			set("*Editable+label", "edition").set("*Editable+sorteable", true);
		def("Editable+Multiple.Size").type("Concept").
			set("lines", 0);

	}


}

