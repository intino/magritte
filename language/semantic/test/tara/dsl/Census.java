package tara.dsl;

import tara.semantic.model.Tara;

import java.util.Locale;

import static tara.semantic.constraints.RuleFactory.*;

public class Census extends Tara {
	public Census() {
		in("").def(context().allow(multiple("Animales"), multiple("Perro"), multiple("Gato"), multiple("Perro"), multiple("Gato"), multiple("Intervencion"), multiple("Term"), name()));
		in("Animales").def(context("Collection", "Concept").allow(multiple("Perro"), multiple("Gato"), multiple("Term"), name()));
		in("Animal.Nombre").def(context("Text", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "string", false, 0, "", "terminalInstance")));
		in("Animal.Foto").def(context("Picture", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "file", false, 0, "", "terminalInstance")));
		in("Animal.NumeroChip").def(context("Text", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "string", false, 0, "", "terminalInstance")));
		in("Animal.FechaNacimiento").def(context("Date", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "date", false, 0, "", "terminalInstance")));
		in("Animal.EsPeligroso").def(context("Boolean", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "boolean", false, 0, "", "terminalInstance")));
		in("Animal.Edad").def(context("Number", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "double", false, 0, "", "terminalInstance")));
		in("Animal.Historial").def(context("Multiple", "Concept").allow(multiple("Intervencion"), multiple("Term"), name()));
		in("Perro").def(context("Form", "Concept").allow(single("Animal.Nombre"), single("Animal.Foto"), single("Animal.NumeroChip"), single("Animal.FechaNacimiento"), single("Animal.EsPeligroso"), single("Animal.Edad"), single("Animal.Historial"), multiple("Term"), name()));
		in("Gato").def(context("Form", "Concept").allow(single("Animal.Nombre"), single("Animal.Foto"), single("Animal.NumeroChip"), single("Animal.FechaNacimiento"), single("Animal.EsPeligroso"), single("Animal.Edad"), single("Animal.Historial"), multiple("Term"), name()));
		in("Intervencion").def(context("Composite", "Concept").allow(multiple("Intervencion.Fecha"), multiple("Intervencion.Tipo"), multiple("Term"), name()));
		in("Intervencion.Fecha").def(context("Date", "Concept").allow(multiple("Term"), name()).require(_parameter("value", "date", false, 0, "", "terminalInstance")));
		in("Intervencion.Tipo").def(context("Select", "Concept").allow(multiple("Term"), name()).require(_parameter("value", new String[]{"Term"}, false, 0, "", "terminalInstance")));
		in("Term").def(context("Concept").allow(multiple("Term"), name(), parameter("code", "integer", false, 0, ""), parameter("label", "string", false, 1, "")).require(_parameter("code", "integer", false, 0, ""), _parameter("label", "string", false, 1, "")).assume(isTerminalInstance()));
	}

	@Override
	public String languageName() {
		return "Census";
	}

	@Override
	public Locale locale() {
		return new Locale("es", "SPAIN", "es_ES");
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
