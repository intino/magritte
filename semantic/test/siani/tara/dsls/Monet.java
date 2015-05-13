package siani.tara.dsls;

import siani.tara.semantic.model.Tag;
import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.constraints.RuleFactory.*;

public class Monet extends Tara {
	public Monet() {
		in("").def(context().allow(multiple("Text"), multiple("Date"), multiple("Boolean"), multiple("Number"), multiple("Select"), multiple("Picture"), multiple("Composite"), multiple("Multiple"), multiple("Text"), multiple("Date"), multiple("Boolean"), multiple("Number"), multiple("Select"), multiple("Picture"), multiple("Composite"), multiple("Multiple"), multiple("Form"), multiple("Collection"), multiple("Thesaurus"), multiple("Form"), multiple("Collection"), multiple("Thesaurus"), multiple("Collectable"), name()));
		in("Text").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "string", true, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _parameter("mode:word", new String[]{"Uppercase", "Lowercase"}, false, 3, "", "readonly")));
		in("Date").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "date", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly")));
		in("Boolean").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "boolean", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly")));
		in("Number").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "double", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly")));
		in("Select").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", new String[]{"Thesaurus.Term"}, false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly")));
		in("Picture").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "file", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly")));
		in("Composite").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), name()).require(_multiple("Text", Tag.SINGLE, Tag.REQUIRED), _multiple("Date", Tag.SINGLE, Tag.REQUIRED), _multiple("Boolean", Tag.SINGLE, Tag.REQUIRED), _multiple("Number", Tag.SINGLE, Tag.REQUIRED), _multiple("Select", Tag.SINGLE, Tag.REQUIRED), _multiple("Picture", Tag.SINGLE, Tag.REQUIRED), _multiple("Composite", Tag.SINGLE, Tag.REQUIRED), _multiple("Multiple", Tag.SINGLE, Tag.REQUIRED), _parameter("label", "string", false, 0, "", "readonly")));
		in("Multiple").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("min", "integer", false, 2, ""), parameter("max", "integer", false, 3, ""), name()).require(oneOf(_single("Text", Tag.MULTIPLE), _single("Date", Tag.MULTIPLE), _single("Boolean", Tag.MULTIPLE), _single("Number", Tag.MULTIPLE), _single("Select", Tag.MULTIPLE), _single("Picture", Tag.MULTIPLE), _single("Composite", Tag.MULTIPLE), _single("Multiple", Tag.MULTIPLE)), _parameter("label", "string", false, 0, "", "readonly")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Entity.Toolbar.Operation").def(context("Concept").allow(name()).require(_parameter("label", "string", false, 0, ""), _parameter("action", "native", false, 1, "Action")));
		in("Form").def(context("Concept").allow(multiple("Entity.Toolbar"), name(), facet("Collectable")).require(_multiple("Text", Tag.SINGLE, Tag.REQUIRED), _multiple("Date", Tag.SINGLE, Tag.REQUIRED), _multiple("Boolean", Tag.SINGLE, Tag.REQUIRED), _multiple("Number", Tag.SINGLE, Tag.REQUIRED), _multiple("Select", Tag.SINGLE, Tag.REQUIRED), _multiple("Picture", Tag.SINGLE, Tag.REQUIRED), _multiple("Composite", Tag.SINGLE, Tag.REQUIRED), _multiple("Multiple", Tag.SINGLE, Tag.REQUIRED)));
		in("Collection").def(context("Concept").allow(multiple("Entity.Toolbar"), single("Collection.Entry"), name()));
		in("Collection.Entry").def(context("Concept").allow(parameter("collectable", new String[]{"Collectable"}, false, 0, "", "terminal"), name()));
		in("Thesaurus").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Thesaurus.Term"), name()).assume(isTerminalInstance()));
		in("Thesaurus.Term").def(context("Concept").allow(multiple("Thesaurus.Term"), name()).require(_parameter("code", "integer", false, 0, ""), _parameter("label", "string", false, 1, "")));
		in("Collectable").def(context("Concept").allow(name()).assume(isFacetInstance()));
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
