package siani.tara.dsls;

import siani.tara.semantic.model.Tag;
import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.constraints.RuleFactory.*;

public class Monet extends Tara {
	public Monet() {
		in("").def(context().allow(multiple("Text"), multiple("Date"), multiple("Boolean"), multiple("Number"), multiple("Select"), multiple("Picture"), multiple("Composite"), multiple("Multiple"), multiple("Text"), multiple("Date"), multiple("Boolean"), multiple("Number"), multiple("Select"), multiple("Picture"), multiple("Composite"), multiple("Multiple"), multiple("Form"), multiple("Collection"), multiple("Thesaurus"), multiple("Form"), multiple("Collection"), multiple("Thesaurus"), multiple("Collectable"), name()));
		in("Text").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "string", true, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _parameter("mode:word", new String[]{"Uppercase", "Lowercase"}, false, 3, "", "readonly"), _plate()));
		in("Date").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "date", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Boolean").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "boolean", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Number").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "double", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Select").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", new String[]{"Thesaurus.Term"}, false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Picture").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("value", "file", false, 2, "", "terminal"), name()).require(_parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Composite").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), name()).require(oneOf(_multiple("Text", Tag.SINGLE, Tag.REQUIRED), _multiple("Date", Tag.SINGLE, Tag.REQUIRED), _multiple("Boolean", Tag.SINGLE, Tag.REQUIRED), _multiple("Number", Tag.SINGLE, Tag.REQUIRED), _multiple("Select", Tag.SINGLE, Tag.REQUIRED), _multiple("Picture", Tag.SINGLE, Tag.REQUIRED), _multiple("Composite", Tag.SINGLE, Tag.REQUIRED), _multiple("Multiple", Tag.SINGLE, Tag.REQUIRED)), _parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Multiple").def(context("Concept").allow(parameter("onChange", "native", false, 1, "OnChange"), parameter("min", "integer", false, 2, ""), parameter("max", "integer", false, 3, ""), name()).require(oneOf(_single("Text"), _single("Date"), _single("Boolean"), _single("Number"), _single("Select"), _single("Picture"), _single("Composite"), _single("Multiple")), _parameter("label", "string", false, 0, "", "readonly"), _plate()));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).require(_plate()).assume(isFeatureInstance()));
		in("Entity.Toolbar.Operation").def(context("Concept").allow(name()).require(_parameter("label", "string", false, 0, ""), _parameter("action", "native", false, 1, "Action"), _plate()));
		in("Form").def(context("Concept").allow(multiple("Entity.Toolbar"), name(), facet("Collectable")).require(oneOf(_multiple("Text", Tag.SINGLE, Tag.REQUIRED), _multiple("Date", Tag.SINGLE, Tag.REQUIRED), _multiple("Boolean", Tag.SINGLE, Tag.REQUIRED), _multiple("Number", Tag.SINGLE, Tag.REQUIRED), _multiple("Select", Tag.SINGLE, Tag.REQUIRED), _multiple("Picture", Tag.SINGLE, Tag.REQUIRED), _multiple("Composite", Tag.SINGLE, Tag.REQUIRED), _multiple("Multiple", Tag.SINGLE, Tag.REQUIRED)), _plate()));
		in("Collection").def(context("Concept").allow(multiple("Entity.Toolbar"), single("Collection.Entry"), name()).require(_plate()));
		in("Collection.Entry").def(context("Concept").allow(parameter("collectable", new String[]{"Collectable"}, false, 0, "", "terminal"), name()).require(_plate()));
		in("Thesaurus").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Thesaurus.Term"), name()).require(_plate()).assume(isTerminalInstance()));
		in("Thesaurus.Term").def(context("Concept").allow(multiple("Thesaurus.Term"), name()).require(_parameter("code", "integer", false, 0, ""), _parameter("label", "string", false, 1, ""), _plate()));
		in("Collectable").def(context("Concept").allow(name()).require(_plate()).assume(isFacetInstance()));
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
