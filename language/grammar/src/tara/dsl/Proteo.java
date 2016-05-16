package tara.dsl;

import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Proteo extends Tara {

	public static final char FACET_SEPARATOR = ':';
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);

	public Proteo(boolean ontology) {
		def(Tara.Root).with(context(Tara.Root).has(ontology ?
			allowedInOntologies() :
			new Constraint[]{
				component(CONCEPT, MULTIPLE),
				component(FACET, MULTIPLE),
				component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
				component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
				component(FACET + FACET_SEPARATOR + METACONCEPT, MULTIPLE),
				component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
				component(METACONCEPT, MULTIPLE),
				component(METAFACET, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + METACONCEPT, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE)}));
		def(CONCEPT).with(context(METACONCEPT).has(name(),
			component(METACONCEPT, MULTIPLE),
			component(CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METACONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE))
			.assume(RuleFactory.isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		if (!ontology) {
			def(FACET + FACET_SEPARATOR + METACONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
			def(FACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
			def(METACONCEPT).with(context(METACONCEPT).has(name(),
				component(METACONCEPT, MULTIPLE),
				component(METAFACET, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + METACONCEPT, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
				component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
				component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
				component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
				component(FACET + FACET_SEPARATOR + METACONCEPT, MULTIPLE),
				component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
				component(CONCEPT, MULTIPLE)));
			def(METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
			def(METAFACET + FACET_SEPARATOR + METACONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
			def(METAFACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
			def(METAFACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
			def(METAFACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(METACONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
		}
	}

	private Constraint[] allowedInOntologies() {
		return new Constraint[]{
			component(CONCEPT, MULTIPLE),
			component(FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE)};
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