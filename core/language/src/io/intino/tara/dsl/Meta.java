package io.intino.tara.dsl;

import io.intino.tara.lang.model.rules.Size;

import java.util.Collections;
import java.util.Locale;

import static io.intino.tara.lang.semantics.constraints.RuleFactory.*;

public class Meta extends Tara {

	private static final char FACET_SEPARATOR = ':';
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);


	public Meta() {
		def(Root).with(context(Root).has(
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.CONCEPT).with(context(ProteoConstants.META_CONCEPT).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)))
				.assume(isTerminal()));
		def(ProteoConstants.FACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.FACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.CONCEPT).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.METAFACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.META_CONCEPT).with(context(ProteoConstants.META_CONCEPT).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET + FACET_SEPARATOR + ProteoConstants.METAFACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.METAFACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.META_CONCEPT).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.CONCEPT).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.METAFACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.METAFACET + FACET_SEPARATOR + ProteoConstants.FACET).with(context(ProteoConstants.METAFACET).has(name(), component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)), component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
	}

	@Override
	public String languageName() {
		return "Meta";
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
