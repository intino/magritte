package tara.dsl;

import tara.lang.model.rules.Size;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.AbstractMap;
import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Proteo extends Tara {

	public static final char FACET_SEPARATOR = ':';
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);

	public Proteo() {
		def(Tara.Root).with(context(Tara.Root).has(
			component(CONCEPT, MULTIPLE),
			component(FACET, MULTIPLE),
			component(FACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(FACET + FACET_SEPARATOR + FACET, MULTIPLE)));
		def(CONCEPT).with(context(META_CONCEPT).has(name(),
			component(META_CONCEPT, MULTIPLE),
			component(CONCEPT, MULTIPLE),
			component(METAFACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + META_CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + CONCEPT, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + FACET, MULTIPLE),
			component(METAFACET + FACET_SEPARATOR + METAFACET, MULTIPLE))
			.assume(RuleFactory.isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, MULTIPLE)).assume(isTerminal()));
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

	public AbstractMap.SimpleEntry id(){
		return new AbstractMap.SimpleEntry(ProteoConstants.PROTEO_GROUP_ID, ProteoConstants.PROTEO_ARTIFACT_ID);
	}
}