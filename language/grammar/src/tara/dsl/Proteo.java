package tara.dsl;

import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.model.rules.Size.MULTIPLE;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Proteo extends Tara {


	public Proteo(boolean ontology) {
		def(Tara.Root).with(context(Tara.Root).has(ontology ?
			allowedInOntologies() :
			new Constraint[]{
				component(CONCEPT, MULTIPLE()),
				component(FACET, MULTIPLE()),
				component(FACET + ':' + CONCEPT, MULTIPLE()),
				component(FACET + ':' + FACET, MULTIPLE()),
				component(FACET + ':' + METACONCEPT, MULTIPLE()),
				component(FACET + ':' + METAFACET, MULTIPLE()),
				component(METACONCEPT, MULTIPLE()),
				component(METAFACET, MULTIPLE()),
				component(METAFACET + ':' + METACONCEPT, MULTIPLE()),
				component(METAFACET + ':' + CONCEPT, MULTIPLE()),
				component(METAFACET + ':' + FACET, MULTIPLE()),
				component(METAFACET + ':' + METAFACET, MULTIPLE())}));
		def(CONCEPT).with(context(METACONCEPT).has(name(),
			component(METACONCEPT, MULTIPLE()),
			component(CONCEPT, MULTIPLE()),
			component(METAFACET, MULTIPLE()),
			component(METAFACET + ':' + METACONCEPT, MULTIPLE()),
			component(METAFACET + ':' + CONCEPT, MULTIPLE()),
			component(METAFACET + ':' + FACET, MULTIPLE()),
			component(METAFACET + ':' + METAFACET, MULTIPLE()))
			.assume(RuleFactory.isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE())).assume(isTerminal()));
		def(FACET + ':' + FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE())).assume(isTerminal()));
		def(FACET + ':' + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE())).assume(isTerminal()));
		if (!ontology) {
			def(FACET + ':' + METACONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE())).assume(isTerminal()));
			def(FACET + ':' + METAFACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE())).assume(isTerminal()));
			def(METACONCEPT).with(context(METACONCEPT).has(name(),
				component(METACONCEPT, MULTIPLE()),
				component(METAFACET, MULTIPLE()),
				component(METAFACET + ':' + METACONCEPT, MULTIPLE()),
				component(METAFACET + ':' + CONCEPT, MULTIPLE()),
				component(METAFACET + ':' + FACET, MULTIPLE()),
				component(METAFACET + ':' + METAFACET, MULTIPLE()),
				component(FACET + ':' + CONCEPT, MULTIPLE()),
				component(FACET + ':' + FACET, MULTIPLE()),
				component(FACET + ':' + METACONCEPT, MULTIPLE()),
				component(FACET + ':' + METAFACET, MULTIPLE()),
				component(CONCEPT, MULTIPLE())));
			def(METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE()), component(CONCEPT, MULTIPLE())));
			def(METAFACET + ':' + METACONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE()), component(CONCEPT, MULTIPLE())));
			def(METAFACET + ':' + CONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE()), component(CONCEPT, MULTIPLE())));
			def(METAFACET + ':' + METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE()), component(CONCEPT, MULTIPLE())));
			def(METAFACET + ':' + FACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE()), component(CONCEPT, MULTIPLE())));
		}
	}

	private Constraint[] allowedInOntologies() {
		return new Constraint[]{
			component(CONCEPT, MULTIPLE()),
			component(FACET, MULTIPLE()),
			component(FACET + ':' + CONCEPT, MULTIPLE()),
			component(FACET + ':' + FACET, MULTIPLE())};
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