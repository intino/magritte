package tara.dsl;

import tara.lang.model.rules.Size;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.Collections;
import java.util.Locale;

import static tara.dsl.ProteoConstants.*;
import static tara.lang.semantics.constraints.RuleFactory.*;

public class Proteo extends Tara {

	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);
	public static final char FACET_SEPARATOR = ':';
	public static final String GROUP_ID = "io.intino.tara";
	public static final String ARTIFACT_ID = "proteo";

	public Proteo() {
		def(Tara.Root).with(context(Tara.Root).has(
				component(CONCEPT, Collections.singletonList(MULTIPLE)),
				component(FACET, Collections.singletonList(MULTIPLE)),
				component(FACET + FACET_SEPARATOR + CONCEPT, Collections.singletonList(MULTIPLE)),
				component(FACET + FACET_SEPARATOR + FACET, Collections.singletonList(MULTIPLE))));
		def(CONCEPT).with(context(META_CONCEPT).has(name(),
				component(META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(CONCEPT, Collections.singletonList(MULTIPLE)),
				component(METAFACET, Collections.singletonList(MULTIPLE)),
				component(METAFACET + FACET_SEPARATOR + META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(METAFACET + FACET_SEPARATOR + CONCEPT, Collections.singletonList(MULTIPLE)),
				component(METAFACET + FACET_SEPARATOR + FACET, Collections.singletonList(MULTIPLE)),
				component(METAFACET + FACET_SEPARATOR + METAFACET, Collections.singletonList(MULTIPLE)))
				.assume(RuleFactory.isTerminal()));
		def(FACET).with(context(METAFACET).has(name(), component(CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + FACET).with(context(METAFACET).has(name(), component(CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(FACET + FACET_SEPARATOR + CONCEPT).with(context(METAFACET).has(name(), component(CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
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