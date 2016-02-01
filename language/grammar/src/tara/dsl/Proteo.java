package tara.dsl;

import tara.lang.model.rules.Size;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;


public class Proteo extends Tara {

	public static final String CONCEPT = "Concept";
	public static final String METACONCEPT = "MetaConcept";
	public static final String FACET = "Facet";
	public static final String METAFACET = "MetaFacet";

	public Proteo() {
		def(Root).with(context(Root).has(
			component(CONCEPT, Size.MULTIPLE), component(METACONCEPT, Size.MULTIPLE), component(FACET, Size.MULTIPLE), component(METAFACET, Size.MULTIPLE),
			component(FACET + ":" + METACONCEPT, Size.MULTIPLE), component(FACET + ":" + CONCEPT, Size.MULTIPLE), component(FACET + ":" + FACET, Size.MULTIPLE), component(FACET + ":" + METAFACET, Size.MULTIPLE),
			component(METAFACET + ":" + METACONCEPT, Size.MULTIPLE), component(METAFACET + ":" + CONCEPT, Size.MULTIPLE), component(METAFACET + ":" + FACET, Size.MULTIPLE), component(METAFACET + ":" + METAFACET, Size.MULTIPLE)));
		def(CONCEPT).with(context(METACONCEPT).has(name(), component(CONCEPT, Size.MULTIPLE), component(METACONCEPT, Size.MULTIPLE)).assume(isTerminal()));
		def(METACONCEPT).with(context(METACONCEPT).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(FACET + ":" + METACONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(FACET + ":" + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(FACET + ":" + FACET).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(FACET + ":" + METAFACET).with(context(METAFACET).has(name(), component(CONCEPT, Size.MULTIPLE)));
		def(METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
		def(METAFACET + ":" + METACONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
		def(METAFACET + ":" + CONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
		def(METAFACET + ":" + METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
		def(METAFACET + ":" + FACET).with(context(METAFACET).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
	}

	@Override
	public String languageName() {
		return "Proteo";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}

	@Override
	public String metaLanguage() {
		return "";
	}
}