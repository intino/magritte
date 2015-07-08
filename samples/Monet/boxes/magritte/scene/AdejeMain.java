package magritte.scene;

import magritte.dsl.CensusDsl;
import magritte.schema.Box;

import static magritte.Tag.Root;

public class AdejeMain extends Box.Scene {
	public static final Box box = new AdejeMain();

	@Override
	public Box[] dependencies() {
		return $(CensusDsl.box);
	}

	@Override
	public void write() {
		def("intervenciones").type("Thesaurus").set(Root).has("intervenciones.vacunacion", "intervenciones.desparasitacion").set("label", "Intervenciones");
		def("intervenciones.vacunacion").type("Thesaurus.Term").set(Root).set("code", 1).set("label", "Vacunación");
		def("intervenciones.desparasitacion").type("Thesaurus.Term").set(Root).set("code", 2).set("label", "Desparasitación");

		def("MiCenso|zunru").type("Censo").set(Root).has($(5));
		def(5).type("Censo.Registro").set("entity", ref("yenpil"));

		def("yenpil").type("Perro").set(Root).has($(11), $(12), $(13), $(14), $(15), $(16), $(17), $(18));
		def(11).type("Animal.Codigo").set("value", "1020");
		def(12).type("Animal.Nombre");
		def(13).type("Animal.Foto").has($(131)).set("value", resource(in("adeje/toby.jpg")));
		def(131).type("Editable.Permission").set("roles", multiple("all"));
		def(14).type("Animal.NumeroChip").set("value", "X2300121");
		def(15).type("Animal.FechaNacimiento").set("value", date(2010, 12, 22));
		def(16).type("Animal.EsPeligroso").set("value", false);
		def(17).type("Animal.Edad").set("value", 4.0);
		def(18).type("Animal.Historial").has($(19), $(22));
		def(19).type("Intervencion").has($(20), $(21));
		def(20).type("Intervencion.Fecha").set("value", date(2011, 2, 5));
		def(21).type("Intervencion.Tipo").set("value", ref("intervenciones.vacunacion"));
		def(22).type("Intervencion").has($(23), $(24));
		def(23).type("Intervencion.Fecha").set("value", date(2012, 4, 5));
		def(24).type("Intervencion.Tipo").set("value", ref("intervenciones.desparasitacion"));
	}


}
