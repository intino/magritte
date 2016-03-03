package tara.dsl;

import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.component;

public class Proteo extends Tara {


	public Proteo(boolean ontology) {
		def(Tara.Root).with(context(Tara.Root).has(ontology ?
			allowedInOntologies() :
			new Constraint[]{
				component(CONCEPT, Size.MULTIPLE()),
				component(FACET, Size.MULTIPLE()),
				component(FACET + ":" + CONCEPT, Size.MULTIPLE()),
				component(FACET + ":" + FACET, Size.MULTIPLE()),
				component(FACET + ":" + METACONCEPT, Size.MULTIPLE()),
				component(FACET + ":" + METAFACET, Size.MULTIPLE()),
				component(METACONCEPT, Size.MULTIPLE()),
				component(METAFACET, Size.MULTIPLE()),
				component(METAFACET + ":" + METACONCEPT, Size.MULTIPLE()),
				component(METAFACET + ":" + CONCEPT, Size.MULTIPLE()),
				component(METAFACET + ":" + FACET, Size.MULTIPLE()),
				component(METAFACET + ":" + METAFACET, Size.MULTIPLE())}));
		def(CONCEPT).with(context(METACONCEPT).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE()), component(METACONCEPT, Size.MULTIPLE())).assume(RuleFactory.isTerminal()));
		def(FACET).with(context(METAFACET).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE())));
		def(FACET + ":" + FACET).with(context(METAFACET).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE())));
		def(FACET + ":" + CONCEPT).with(context(METAFACET).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE())));
		if (!ontology) {
			def(FACET + ":" + METACONCEPT).with(context(METAFACET).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE())));
			def(FACET + ":" + METAFACET).with(context(METAFACET).has(RuleFactory.name(), component(CONCEPT, Size.MULTIPLE())));
			def(METACONCEPT).with(context(METACONCEPT).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
			def(METAFACET).with(context(METAFACET).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
			def(METAFACET + ":" + METACONCEPT).with(context(METAFACET).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
			def(METAFACET + ":" + CONCEPT).with(context(METAFACET).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
			def(METAFACET + ":" + METAFACET).with(context(METAFACET).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
			def(METAFACET + ":" + FACET).with(context(METAFACET).has(RuleFactory.name(), component(METACONCEPT, Size.MULTIPLE()), component(CONCEPT, Size.MULTIPLE())));
		}
	}

	private Constraint[] allowedInOntologies() {
		return new Constraint[]{
			component(CONCEPT, Size.MULTIPLE()),
			component(FACET, Size.MULTIPLE()),
			component(FACET + ":" + CONCEPT, Size.MULTIPLE()),
			component(FACET + ":" + FACET, Size.MULTIPLE())};
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