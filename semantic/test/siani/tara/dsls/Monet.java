package siani.tara.dsls;


import siani.tara.semantic.model.Tag;
import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.Relation.*;
import static siani.tara.semantic.constraints.RuleFactory.*;


public class Monet extends Tara {
	public Monet() {
		in("").def(context().allow(multiple("Form", COMPONENT), multiple("Collection", COMPONENT), multiple("Form", COMPONENT), multiple("Collection", COMPONENT), multiple("Text", COMPONENT), multiple("Date", COMPONENT), multiple("Boolean", COMPONENT), multiple("Number", COMPONENT), multiple("Select", COMPONENT), multiple("Picture", COMPONENT), multiple("Composite", COMPONENT), multiple("Multiple", COMPONENT), multiple("Text", COMPONENT), multiple("Date", COMPONENT), multiple("Boolean", COMPONENT), multiple("Number", COMPONENT), multiple("Select", COMPONENT), multiple("Picture", COMPONENT), multiple("Composite", COMPONENT), multiple("Multiple", COMPONENT), multiple("Term", COMPONENT), name()));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation", COMPONENT), name()).assume(isFeatureInstance()));
		in("Entity.Toolbar.Operation").def(context("Concept").allow(name()).require(_parameter("label", "string", false, 0, ""), _parameter("action", "native", false, 1, "Action")));
		in("Form").def(context("Concept").allow(multiple("Entity.Toolbar", COMPONENT), multiple("Text", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Date", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Boolean", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Number", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Select", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Picture", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Composite", COMPONENT, Tag.SINGLE, Tag.COMPONENT), multiple("Multiple", COMPONENT, Tag.SINGLE, Tag.COMPONENT), name()).require(_address()));
		in("Collection").def(context("Concept").allow(multiple("Entity.Toolbar", COMPONENT), multiple("Form", COMPONENT, Tag.AGGREGATED), name()).require(_address()));
		in("Text").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "string", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _parameter("mode:word", new String[]{"Uppercase", "Lowercase"}, false, 3, ""), _address()));
		in("Date").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "date", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Boolean").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "boolean", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Number").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "double", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Select").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", new String[]{"Term"}, false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Picture").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "file", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Composite").def(context("Concept").allow(multiple("Text", COMPONENT, Tag.COMPONENT), multiple("Date", COMPONENT, Tag.COMPONENT), multiple("Boolean", COMPONENT, Tag.COMPONENT), multiple("Number", COMPONENT, Tag.COMPONENT), multiple("Select", COMPONENT, Tag.COMPONENT), multiple("Picture", COMPONENT, Tag.COMPONENT), multiple("Composite", COMPONENT, Tag.COMPONENT), multiple("Multiple", COMPONENT, Tag.COMPONENT), parameter("onChange", "native", false, 1, "OnChange"), name()).require(_parameter("label", "string", false, 0, ""), _address()));
		in("Multiple").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), name()).require(oneOf(_single("Text", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Date", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Boolean", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Number", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Select", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Picture", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Composite", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE), _single("Multiple", COMPONENT, Tag.AGGREGATED, Tag.MULTIPLE)), _parameter("label", "string", false, 0, ""), _address()));
		in("Term").def(context("Concept").allow(multiple("Term", COMPONENT, Tag.COMPONENT), name()).require(_parameter("code", "integer", false, 0, ""), _parameter("label", "string", false, 1, "")).assume(isTerminalInstance()));
	}


	@Override
	public String languageName() {
		return "Monet";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
