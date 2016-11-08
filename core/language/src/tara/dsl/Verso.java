package tara.dsl;

import tara.lang.model.rules.Size;

import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Verso extends Tara {

	private static final char FACET_SEPARATOR = ':';
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);


	public Verso() {
		def(Tara.Root).with(context(Tara.Root).has(
			component(CONCEPT, MULTIPLE),
			component(FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(META_CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE)));
		def(CONCEPT).with(context(META_CONCEPT).has(name(),
			component(META_CONCEPT, MULTIPLE),
			component(CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE))
			.assume(isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + META_CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE), component(META_CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE), component(META_CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(META_CONCEPT).with(context(META_CONCEPT).has(name(),
			component(META_CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + METAFACET, MULTIPLE),
			component(CONCEPT, MULTIPLE)));
		def(METAFACET).with(context(METAFACET).has(name(), component(META_CONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + META_CONCEPT).with(context(METAFACET).has(name(), component(META_CONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(META_CONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + METAFACET).with(context(METAFACET).has(name(), component(META_CONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
		def(METAFACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(META_CONCEPT, MULTIPLE), component(CONCEPT, MULTIPLE)));
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
