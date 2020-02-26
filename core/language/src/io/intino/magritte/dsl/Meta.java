package io.intino.magritte.dsl;

import io.intino.magritte.lang.model.rules.Size;

import java.util.Collections;
import java.util.Locale;

import static io.intino.magritte.lang.semantics.constraints.RuleFactory.*;

public class Meta extends Tara {
	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);

	public Meta() {
		def(Root).with(context(Root).has(
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.ASPECT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.CONCEPT).with(context(ProteoConstants.META_CONCEPT).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.ASPECT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_ASPECT, Collections.singletonList(MULTIPLE)))
				.assume(isTerminal()));
		def(ProteoConstants.META_CONCEPT).with(context(ProteoConstants.META_CONCEPT).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_ASPECT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.ASPECT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.ASPECT).with(context(ProteoConstants.META_ASPECT).has(name(),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
		def(ProteoConstants.META_ASPECT).with(context(ProteoConstants.META_ASPECT).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.FACET).with(context(ProteoConstants.FACET).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
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
