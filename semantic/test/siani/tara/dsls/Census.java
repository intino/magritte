package siani.tara.dsls;

import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.Relation.AGGREGATED;
import static siani.tara.semantic.Relation.COMPONENT;
import static siani.tara.semantic.constraints.RuleFactory.*;

public class Census extends Tara {
	public Census() {
		in("").def(context().allow(multiple("Animales", COMPONENT), multiple("Perro", COMPONENT), multiple("Gato", COMPONENT), multiple("Perro", COMPONENT), multiple("Gato", COMPONENT), multiple("Intervencion", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Animales").def(context("Collection", "Concept").allow(multiple("Perro", COMPONENT), multiple("Gato", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Animal.Nombre").def(context("Text", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "string", false, 0, "", "terminalInstance")));
		in("Animal.Foto").def(context("Picture", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "file", false, 0, "", "terminalInstance")));
		in("Animal.NumeroChip").def(context("Text", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "string", false, 0, "", "terminalInstance")));
		in("Animal.FechaNacimiento").def(context("Date", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "date", false, 0, "", "terminalInstance")));
		in("Animal.EsPeligroso").def(context("Boolean", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "boolean", false, 0, "", "terminalInstance")));
		in("Animal.Edad").def(context("Number", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "double", false, 0, "", "terminalInstance")));
		in("Animal.Historial").def(context("Multiple", "Concept").allow(multiple("Intervencion", AGGREGATED), multiple("Term", COMPONENT), name()));
		in("Perro").def(context("Form", "Concept").allow(single("Animal.Nombre", COMPONENT), single("Animal.Foto", COMPONENT), single("Animal.NumeroChip", COMPONENT), single("Animal.FechaNacimiento", COMPONENT), single("Animal.EsPeligroso", COMPONENT), single("Animal.Edad", COMPONENT), single("Animal.Historial", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Gato").def(context("Form", "Concept").allow(single("Animal.Nombre", COMPONENT), single("Animal.Foto", COMPONENT), single("Animal.NumeroChip", COMPONENT), single("Animal.FechaNacimiento", COMPONENT), single("Animal.EsPeligroso", COMPONENT), single("Animal.Edad", COMPONENT), single("Animal.Historial", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Intervencion").def(context("Composite", "Concept").allow(multiple("Intervencion.Fecha", COMPONENT), multiple("Intervencion.Tipo", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Intervencion.Fecha").def(context("Date", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", "date", false, 0, "", "terminalInstance")));
		in("Intervencion.Tipo").def(context("Select", "Concept").allow(multiple("Term", COMPONENT), name()).require(_parameter("value", new String[]{"Term"}, false, 0, "", "terminalInstance")));
		in("Term").def(context("Concept").allow(multiple("Term", COMPONENT), name(), parameter("code", "integer", false, 0, ""), parameter("label", "string", false, 1, "")).require(_parameter("code", "integer", false, 0, ""), _parameter("label", "string", false, 1, "")).assume(isTerminalInstance()));
	}

	@Override
	public String languageName() {
		return "Census";
	}

	@Override
	public Locale locale() {
		return new Locale("spanish", "SPAIN", "es_ES");
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
