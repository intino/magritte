package magritte.ontology;

import siani.tara.magritte.Expression;
import magritte.dsl.MonetDsl;
import siani.tara.magritte.schema.Box;

import static siani.tara.magritte.Tag.*;

public class CensusMain extends Box.Ontology {
	public static final Box box = new CensusMain();

	@Override
	public Box[] dependencies() {
		return $(MonetDsl.box);
	}

	@Override
	public void write() {
		def("Censo|brasmas").type("Collection").set(Main, Singleton).holds("Censo.Registro");
		def("Censo.Registro|vurlo").type("Collection.Entry").set("?entity", ref("Animal"));

		def("Animal|dogon").type("Form").set(Main, Abstract).has($(4)).requires("Animal.Codigo", "Animal.Nombre", "Animal.Foto", "Animal.NumeroChip", "Animal.FechaNacimiento", "Animal.EsPeligroso", "Animal.Edad", "Animal.Historial");
		def(4).type("Entity.Toolbar").set(Case).has($(5));
		def(5).type("Entity.Toolbar.Operation").set(Case).set("label", "Cambiar chip").let("action", new AnimalOperation5Action());

		def("Animal.Codigo|tlenpo").type("Serie").set("label", "Codigo").set("*value", "default").let("onChange", new AnimalCodigoOnChange());
		def("Animal.Nombre|posti").type("Text").set("label", "Nombre").let("*value", new AnimalNombreValue());
		def("Animal.Foto|cirdras").
			type("Picture").type("Editable+Picture").
			has($(1)).
			set("label", "Foto").set("*value", resource(in("census/default.png"))).
			set("Editable+label", "edit now");
		def(1).type("Editable+Picture.Format").set(Case, Prototype).set("extension", "jpg");
		def("Animal.NumeroChip|fecsis").type("Text").set("label", "Numero de chip").set("mode", 0);
		def("Animal.FechaNacimiento|delroc").type("Date").set("label", "Fecha de nacimiento");
		def("Animal.EsPeligroso|bradon").type("Boolean").set("label", "¿Es peligroso?").set("*value", false);
		def("Animal.Edad|ramisli").type("Number").set("label", "Edad");
		def("Animal.Historial|rioma").
			type("Multiple").type("Editable+Multiple").
			has($(2), $(3)).
			requires("Intervencion").holds("Intervencion").
			set("label", "Historial");
		def(2).type("Editable.Permission").set(Case, Prototype).set("roles", multiple("boss"));
		def(3).type("Editable+Multiple.Size").set(Case).set("lines", 4);
		def("Intervencion|gankul").type("Composite").requires("Intervencion.Fecha", "Intervencion.Tipo").set("label", "Intervención");
		def("Intervencion.Fecha|gelvasco").type("Date").set("label", "Fecha");
		def("Intervencion.Tipo|cirac").type("Select").set("label", "Tipo de intervención");
		def("Perro|jiskot").type("Form").parent("Animal").set(Main);
		def("Gato|yasjoc").type("Form").parent("Animal").set(Main);
	}

	public static class AnimalOperation5Action extends census.Animal implements monet.natives.Action {

		@Override
		public void execute() {
			numeroChip().value("X-2100");
		}


	}

	public static class AnimalCodigoOnChange extends census.Animal implements monet.natives.Action {

		@Override
		public void execute() {
			numeroChip().value(codigo().value());
		}
	}


	public static class AnimalNombreValue extends census.Animal implements Expression<String> {
		@Override
		public String value() {
			return codigo().value();
		}
	}

}

