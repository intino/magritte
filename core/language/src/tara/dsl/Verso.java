package tara.dsl;

import tara.lang.model.rules.Size;

import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Verso extends Tara {

	public static final char FACET_SEPARATOR = ':';
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);


	public Verso() {
		def(Tara.Root).with(context(Tara.Root).has(
			component(CONCEPT, MULTIPLE),
			component(FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + MetaConcept, MULTIPLE),
			component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(MetaConcept, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + MetaConcept, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE)));
		def(CONCEPT).with(context(MetaConcept).has(name(),
			component(MetaConcept, MULTIPLE),
			component(CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + MetaConcept, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE))
			.assume(isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + MetaConcept).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(MetaConcept).with(context(MetaConcept).has(name(),
			component(MetaConcept, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + MetaConcept, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + MetaConcept, MULTIPLE),
			component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(CONCEPT, MULTIPLE)));
		def(METAFACET).with(context(METAFACET).has(name(), component(MetaConcept, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + MetaConcept).with(context(METAFACET).has(name(), component(MetaConcept, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(MetaConcept, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(MetaConcept, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(MetaConcept, MULTIPLE), component(CONCEPT, MULTIPLE)));
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
